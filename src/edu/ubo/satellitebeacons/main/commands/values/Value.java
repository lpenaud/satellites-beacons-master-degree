package edu.ubo.satellitebeacons.main.commands.values;

public interface Value<T> {
  public static Value<Object> UNDEFINED_VALUE = new Value<Object>() {
    @Override
    public Object getValue() {
      return null;
    }
    
    @Override
    public String toString() {
      return "undefined";
    }
  };
  
  T getValue();
  String toString();
}
