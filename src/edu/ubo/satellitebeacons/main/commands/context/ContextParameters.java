package edu.ubo.satellitebeacons.main.commands.context;

import java.io.InputStream;
import java.io.PrintStream;
import edu.ubo.satellitebeacons.main.commands.Command;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.commands.constructors.Constructors;
import edu.ubo.satellitebeacons.main.commands.values.StringValue;
import edu.ubo.satellitebeacons.main.commands.values.Value;

public interface ContextParameters {
  public interface ClassEntry<T> {
    Class<T> getType();
    Command<T> getContructor();
  }
  
  public interface ContextGlobalVar<T> {
    String getName();
    Value<T> getValue();
  }
  
  public class ClassEntryImpl<T> implements ClassEntry<T> {
    
    public ClassEntryImpl(final Class<T> type, final Command<T> constructor) {
      this.type = type;
      this.constructor = constructor;
    }

    @Override
    public Class<T> getType() {
      return type;
    }

    @Override
    public Command<T> getContructor() {
      return constructor;
    }
    
    private final Class<T> type;
    private final Command<T> constructor;    
  }
  
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
  
  @SuppressWarnings("unchecked")
  default ClassEntry<Object>[] getClasses() {
    final var constructors = new Constructors();
    final var classes = new ClassEntry[2];
    classes[0] = new ClassEntryImpl<>(Satellite.class, constructors::newSatellite);
    classes[1] = new ClassEntryImpl<>(Beacon.class, constructors::newBeacon);
    return classes;
  }
  
  default ContextGlobalVar<?>[] getGlobals() {
    final ContextGlobalVar<?>[] globals = new ContextGlobalVar[1];
    globals[0] = new ContextGlobalVar<String>() {
      @Override
      public String getName() {
        return "PWD";
      }
      
      @Override
      public Value<String> getValue() {
        return new StringValue(System.getProperty("user.dir"));
      }
    };
    return globals;
  }
}
