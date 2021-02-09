package edu.ubo.satellitebeacons.main.commands.context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import edu.ubo.satellitebeacons.main.annotations.ScriptClass;
import edu.ubo.satellitebeacons.main.commands.Command;
import edu.ubo.satellitebeacons.main.commands.context.ContextParameters.ClassEntry;
import edu.ubo.satellitebeacons.main.commands.exceptions.ReferenceException;
import edu.ubo.satellitebeacons.main.commands.exceptions.TypeException;
import edu.ubo.satellitebeacons.main.commands.values.MapValue;
import edu.ubo.satellitebeacons.main.commands.values.NumberValue;
import edu.ubo.satellitebeacons.main.commands.values.ObjectValue;
import edu.ubo.satellitebeacons.main.commands.values.Value;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconBaseVisitor;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconLexer;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.AffectationInstanceContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.AffectationNbContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.ArgsContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.MethodContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.PropertiesContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.VariableContext;

public class Context extends SatelliteBeaconBaseVisitor<Object> implements Callable<Void> {
  static class Line {
    Line(final int nb, final String content) {
      this.nb = nb;
      this.content = content;
    }

    public int getNb() {
      return nb;
    }

    public String getContent() {
      return content;
    }

    protected final int nb;
    protected final String content;

  }

  public Context() throws Exception {
    this(new ContextParameters() {});
  }

  public Context(final ContextParameters parameters) throws Exception {
    this.classes = new HashMap<>();
    this.variables = new HashMap<>();
    this.reservedWords = new HashSet<>();
    this.it = this.lineIterator();
    this.prompt = parameters.getPrompt();
    this.stdout = parameters.getStdout();
    this.stderr = parameters.getStderr();
    this.stdin = new BufferedReader(new InputStreamReader(parameters.getStdin()));
    this.addClasses(parameters.getClasses());
    parameters.getGlobals().forEach(this::addGlobal);
    this.addGlobal("global", new MapValue("Global", variables));
  }

  public void addGlobal(final String name, final Value<?> value) {
    this.variables.put(name, value);
    this.reservedWords.add(name);
  }

  public void addClasses(final ClassEntry<Object>[] entries) throws Exception {
    for (final var entry : entries) {
      this.addClass(entry.getType(), entry.getContructor());
    }
  }

  public <C> void addClass(final Class<C> cl, Command<C> command) throws Exception {
    final var anno = cl.getAnnotation(ScriptClass.class);
    if (anno == null) {
      throw new Exception(new StringBuilder(cl.getName()).append(" must have the annotation")
          .append(ScriptClass.class.getName()).toString());
    }
    this.classes.put(anno.value(), command);
  }

  @Override
  public Void call() throws Exception {
    final SatelliteBeaconParser parser = new SatelliteBeaconParser(null);
    Line line;
    do {
      this.stdout.print(this.prompt);
      line = this.it.next();
      final CharStream stream = CharStreams.fromString(line.content);
      final SatelliteBeaconLexer lexer = new SatelliteBeaconLexer(stream);
      lexer.setLine(line.nb);
      lexer.setCharPositionInLine(0);
      final TokenStream tokens = new CommonTokenStream(lexer);
      parser.setInputStream(tokens);
      final ParseTree tree = parser.command();
      this.visit(tree);
    } while (this.it.hasNext());
    return null;
  }

  @Override
  public Object visitAffectationNb(AffectationNbContext ctx) {
    final var name = ctx.WORD().getText();
    final var value = new NumberValue(Integer.parseInt(ctx.NB().getText()));
    this.variables.put(name, value);
    this.stdout.println(value.pretty());
    return super.visitAffectationNb(ctx);
  }

  @Override
  public Object visitAffectationInstance(AffectationInstanceContext ctx) {
    final var callableCtx = ctx.newInstance().callable();
    final var className = callableCtx.WORD().getText();
    final var construtor = this.classes.get(className);
    if (construtor == null) {
      return this.printException(new ClassNotFoundException(new StringBuilder("Class \"")
          .append(className).append("\" is unknow in this context").toString()));
    }
    final Map<String, String> argsMap;
    if (callableCtx.args() != null) {
      argsMap = callableCtx.args().args().stream()
          .collect(Collectors.toMap(this::getArgName, this::getArgValue));
      argsMap.put(this.getArgName(callableCtx.args()), this.getArgValue(callableCtx.args()));
    } else {
      argsMap = new HashMap<>(0);
    }
    final var name = ctx.WORD().getText();
    final var value = new ObjectValue(construtor.call(argsMap));
    this.variables.put(name, value);
    this.stdout.println(value.pretty());
    return super.visitAffectationInstance(ctx);
  }

  @Override
  public Object visitVariable(VariableContext ctx) {
    final var name = ctx.WORD().getText();
    final var value = this.variables.getOrDefault(name, Value.UNDEFINED_VALUE);
    this.stdout.println(value.pretty());
    return super.visitVariable(ctx);
  }
  
  @Override
  public Object visitProperties(PropertiesContext ctx) {
    var name = ctx.WORD().getText();
    Value<?> obj = this.variables.getOrDefault(name, Value.UNDEFINED_VALUE);
    final var props = ctx.property();
    try {
      for (int i = 0; i < props.size(); i++) {
        name = props.get(i).WORD().getText();
        obj = obj.getProperty(name);
        if (obj == null) {
          return this.printException(new TypeException(name));
        }
      }
    } catch (TypeException e) {
      return this.printException(e);
    }
    this.stdout.println(obj.pretty());
    return super.visitProperties(ctx);
  }

  @Override
  public Object visitMethod(MethodContext ctx) {
    // final var objName = ctx.WORD() != null ? ctx.WORD().getText() : "this";
    // final var obj = this.variables.get(objName);
    // if (obj == null) {
    // this.printError(new TypeException(objName));
    // }
    // final var method = ctx.callable().WORD();
    return super.visitMethod(ctx);
  }

  protected String getArgName(final ArgsContext ctx) {
    return ctx.WORD().getText();
  }

  protected String getArgValue(final ArgsContext ctx) {
    return ctx.NB().getText();
  }

  protected Object printException(final Exception e) {
    this.stderr.println(
        new StringBuilder(e.getClass().getSimpleName()).append(": ").append(e.getMessage()));
    return null;
  }

  protected Iterator<Line> lineIterator() {
    return new Iterator<Context.Line>() {

      @Override
      public boolean hasNext() {
        return line.content != null;
      }

      @Override
      public Line next() {
        try {
          line = new Line(line.nb + 1, stdin.readLine());
        } catch (IOException e) {
          throw new NoSuchElementException();
        }
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return line;
      }

      private Line line = new Line(0, "");

    };
  }

  protected final CharSequence prompt;
  protected final PrintStream stdout;
  protected final PrintStream stderr;
  protected final BufferedReader stdin;
  protected final Iterator<Line> it;
  protected final Map<String, Value<?>> variables;
  protected final Map<String, Command<?>> classes;
  protected final Set<String> reservedWords;

}
