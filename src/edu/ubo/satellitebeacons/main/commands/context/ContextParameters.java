package edu.ubo.satellitebeacons.main.commands.context;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import edu.ubo.satellitebeacons.main.commands.Command;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.movable.movement.HorizontalMovement;
import edu.ubo.satellitebeacons.main.movable.movement.SimpleHorizontalMovement;
import edu.ubo.satellitebeacons.main.simulation.Simulation;
import edu.ubo.satellitebeacons.main.commands.constructors.Constructors;
import edu.ubo.satellitebeacons.main.commands.values.StringValue;
import edu.ubo.satellitebeacons.main.commands.values.Value;

public interface ContextParameters {
  default PrintStream getStdout() {
    return System.out;
  }

  default PrintStream getStderr() {
    return System.err;
  }

  default InputStream getStdin() {
    return System.in;
  }

  default CharSequence getPrompt() {
    return ">>> ";
  }

  default Map<Class<?>, Command<Object>> getClasses() {
    final var constructors = new Constructors();
    return Map.of(Satellite.class, constructors::newSatellite, Beacon.class,
        constructors::newBeacon, HorizontalMovement.class, constructors::newHorizontalMovement,
        SimpleHorizontalMovement.class, constructors::newVerticalMovement);
  }

  default Map<String, Value<?>> getGlobals() {
    return Map.of("PWD", new StringValue(System.getProperty("user.dir")));
  }

  default Simulation getSimulation() {
    return new Simulation();
  }

  default ExecutorService getExecutor() {
    return Executors.newSingleThreadExecutor();
  }

  default BiConsumer<Context, Value<?>> getActionOnReturn() {
    return (ctx, v) -> ctx.stdout.println(v.pretty());
  }
  
  default Consumer<Context> getActionBeforeCommand() {
    return ctx -> ctx.stdout.print(ctx.prompt);
  }
}
