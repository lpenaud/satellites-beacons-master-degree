package edu.ubo.satellitebeacons.main.commands.context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import edu.ubo.satellitebeacons.main.annotations.ScriptClass;
import edu.ubo.satellitebeacons.main.commands.Command;
import edu.ubo.satellitebeacons.main.commands.context.ContextParameters.ClassEntry;
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
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.GlobalCallableContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.PropertiesContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.VariableContext;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.simulation.Simulation;
import edu.ubo.satellitebeacons.main.utils.Utils;

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
    this.simulation = parameters.getSimulation();
    this.executor = parameters.getExecutor();
    parameters.getGlobals().forEach(this::addGlobal);
    this.addGlobal("global", new MapValue("Global", variables));
    this.simulation.open();
    this.addCommand("start", args -> {
      this.executor.submit(this.simulation);
      return Value.UNDEFINED;
    });
    this.addCommand("add", args -> {
      Object obj = args.get("s");
      if (obj != null) {
        this.simulation.addSatellite((Satellite) obj);
        return obj;
      }
      obj = args.get("b");
      if (obj != null) {
        this.simulation.addBeacon((Beacon) obj);
        return obj;
      }
      return Value.UNDEFINED;
    });
  }

  public void addCommand(final String name, final Command<?> command) {
    this.addGlobal(name, new Value<Command<?>>() {
      @Override
      public Command<?> getValue() {
        return command;
      }

      @Override
      public CharSequence pretty(int level) {
        return new StringBuilder(name).append("()");
      }

      @Override
      public Value<?> getProperty(String attribute) throws TypeException {
        throw new TypeException(new StringBuilder(name).append(" has no attribute").toString());
      }
    });
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
    try {
      final var name = ctx.WORD().get(0).getText();
      final var value = new NumberValue(Integer.parseInt(ctx.WORD().get(1).getText()));
      this.variables.put(name, value);
      this.stdout.println(value.pretty());
    } catch (Exception e) {
      this.printException(e);
    }
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
    final Map<String, Object> argsMap = this.readArgs(callableCtx.args());
    final var name = ctx.WORD().getText();
    final var value = new ObjectValue(construtor.call(argsMap));
    this.variables.put(name, value);
    this.stdout.println(value.pretty());
    return super.visitAffectationInstance(ctx);
  }

  @Override
  public Object visitVariable(VariableContext ctx) {
    final var name = ctx.WORD().getText();
    final var value = this.variables.getOrDefault(name, Value.UNDEFINED);
    this.stdout.println(value.pretty());
    return super.visitVariable(ctx);
  }

  @Override
  public Object visitProperties(PropertiesContext ctx) {
    var name = ctx.WORD().getText();
    Value<?> obj = this.variables.getOrDefault(name, Value.UNDEFINED);
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
  public Object visitGlobalCallable(GlobalCallableContext ctx) {
    final var variable =
        this.variables.getOrDefault(ctx.callable().WORD().getText(), Value.UNDEFINED);
    try {
      final var result = ((Command<?>) variable.getValue()).call(this.readArgs(ctx.callable().args()));
      this.stdout.println(Utils.prettyPrint(result));
    } catch (Exception e) {
      this.printException(e);
    }
    return super.visitGlobalCallable(ctx);
  }

  protected Map<String, Object> readArgs(final ArgsContext args) {
    if (args == null) {
      return Collections.emptyMap();
    }
    final Map<String, Object> argsMap =
        args.args().stream().collect(Collectors.toMap(this::getArgName, this::getArgValue));
    argsMap.put(this.getArgName(args), this.getArgValue(args));
    return argsMap;
  }

  protected String getArgName(final ArgsContext ctx) {
    return ctx.WORD(0).getText();
  }

  protected Object getArgValue(final ArgsContext ctx) {
    final var strValue = ctx.WORD(1).getText();
    final var value = this.variables.get(strValue);
    if (value == null) {
      return strValue;
    }
    return value.getValue();
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
  protected final ExecutorService executor;
  protected final Simulation simulation;

}
