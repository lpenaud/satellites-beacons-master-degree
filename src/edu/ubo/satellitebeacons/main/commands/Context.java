package edu.ubo.satellitebeacons.main.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconBaseVisitor;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconLexer;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.AffectationInstanceContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.AffectationNbContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.AffectationStringContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.ArgsContext;
import edu.ubo.satellitebeacons.main.generated.SatelliteBeaconParser.GlobalsContext;
import edu.ubo.satellitebeacons.main.movable.Satellite;

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

  public Context(final PrintStream out, final InputStream in) {
    this.stderr = out;
    this.stdin = new BufferedReader(new InputStreamReader(in));
    this.it = this.lineIterator();
    this.classes = new HashMap<>();
    this.variables = new HashMap<>();
    this.reservedWords = new HashSet<>();
  }

  public void addGlobal(final String name, final Object value) {
    this.variables.put(name, value);
    this.reservedWords.add(name);
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
      this.stderr.print(">>> ");
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
  public Object visitGlobals(GlobalsContext ctx) {
    this.variables.entrySet().stream().filter(entry -> this.reservedWords.contains(entry.getKey()))
        .forEach(this.stderr::println);
    return super.visitGlobals(ctx);
  }

  @Override
  public Object visitAffectationString(AffectationStringContext ctx) {
    final String name = ctx.WORD().getText();
    final String value = ctx.STRING().getText();
    this.variables.put(name, value);
    this.stderr.println(new StringBuilder(name).append(" = \"").append(value).append('"'));
    return super.visitAffectationString(ctx);
  }

  @Override
  public Object visitAffectationNb(AffectationNbContext ctx) {
    final String name = ctx.WORD().getText();
    final int value = Integer.parseInt(ctx.NB().getText());
    this.variables.put(name, value);
    this.stderr.println(new StringBuilder(name).append(" = ").append(value));
    return super.visitAffectationNb(ctx);
  }

  @Override
  public Object visitAffectationInstance(AffectationInstanceContext ctx) {
    final var callableCtx = ctx.newInstance().callable();
    final var construtor = this.classes.get(callableCtx.WORD().getText());
    if (construtor == null) {
      this.stderr.println(new StringBuilder(ctx.WORD().getText()).append(" is a unknow class"));
    }
    final var args = callableCtx.args();
    final var argsMap =
        args.stream().collect(Collectors.toUnmodifiableMap((a) -> a.WORD().getText(),
            a -> a.NB() != null ? a.NB().getText() : a.STRING().getText()));
    final var name = ctx.WORD().getText();
    final var value = construtor.call(argsMap);
    this.variables.put(name, value);
    this.stderr.println(new StringBuilder(name).append(" = ").append(value));
    return super.visitAffectationInstance(ctx);
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

  protected final BufferedReader stdin;
  protected final PrintStream stderr;
  protected final Iterator<Line> it;
  protected final Map<String, Object> variables;
  protected final Map<String, Command<?>> classes;
  protected final Set<String> reservedWords;

}
