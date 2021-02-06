package edu.ubo.satellitebeacons.main.commands.values;

import edu.ubo.satellitebeacons.main.utils.Utils;

public class ObjectValue implements Value<Object> {
  public ObjectValue(final Object value) {
    this.value = value;
  }

  @Override
  public Object getValue() {
    return value;
  }

  @Override
  public String toString() {
    return Utils.prettyPrint(value).toString();
  }

  protected final Object value;
}
