package edu.ubo.satellitebeacons.main.commands.values;

import java.util.function.Function;
import edu.ubo.satellitebeacons.main.commands.Command;
import edu.ubo.satellitebeacons.main.commands.exceptions.TypeException;

public interface Value<T> {
  public static Value<Object> UNDEFINED = new Value<Object>() {
    @Override
    public Object getValue() {
      return null;
    }

    public Value<?> getProperty(final String name) throws TypeException {
      throw new TypeException(String.format("Cannot read property '%s' of undefined", name));
    }

    @Override
    public CharSequence pretty(final int level) {
      return "undefined";
    }
  };

  T getValue();

  CharSequence pretty(int level);

  default CharSequence pretty() {
    return this.pretty(0);
  }

  Value<?> getProperty(String attribute) throws TypeException;

  default Command<?> getCommand(String attribute) throws TypeException {
    throw new TypeException(String.format("%s is not a method", attribute));
  }
}
