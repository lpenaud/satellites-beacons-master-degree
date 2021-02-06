package edu.ubo.satellitebeacons.main.utils;

public enum PrettyFormatterEnum implements Formatter<Object> {
  NB(String::valueOf),
  STRING(o -> new StringBuilder("\"").append(o.toString()).append('"'));
  
  @Override
  public CharSequence format(final Object o) {
    return formatter.format(o);
  }
  
  private PrettyFormatterEnum(final Formatter<Object> formatter) {
    this.formatter = formatter;
  }
  
  private final Formatter<Object> formatter;
}
