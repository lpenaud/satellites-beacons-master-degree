package edu.ubo.satellitebeacons.main.commands.values;

import edu.ubo.satellitebeacons.main.utils.PrettyFormatterEnum;

public class NumberValue implements Value<Number> {

  public NumberValue(final Number nb) {
    this.value = nb;
  }

  @Override
  public Number getValue() {
    return value;
  }
  
  @Override
  public String toString() {
    return PrettyFormatterEnum.NB.format(value).toString();
  }

  protected final Number value;
}
