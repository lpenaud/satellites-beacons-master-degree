package edu.ubo.satellitebeacons.main.commands.values;

import java.util.Map;

import edu.ubo.satellitebeacons.main.commands.exceptions.TypeException;

public interface Value<T> {  
  public static Value<Object> UNDEFINED_VALUE = new Value<Object>() {
    @Override
    public Object getValue() {
      return null;
    }
    
    public Value<?> getAttribute(final String name) throws TypeException {
      throw new TypeException(String.format("Cannot read property '%s' of undefined", name));
    }

    @Override
    public CharSequence pretty(final int level) {
      return "undefined";
    }
  };
  
  Value<?> getAttribute(String attribute) throws TypeException;
  default Value<?> setAttribute(String attribute, Value<?> value) throws TypeException {
	  throw new TypeException("");
  }
  T getValue();
  CharSequence pretty(int level);

  default CharSequence pretty() {
    return this.pretty(0);
  }
  
  default boolean isCallable() {
	  return false;
  }
  
  default Value<?> call(String attribute, Map<String, String> args) throws TypeException {
	  throw new TypeException(String.format("%s is not a callable", attribute));
  }
}
