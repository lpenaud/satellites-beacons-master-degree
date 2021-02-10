package edu.ubo.satellitebeacons.main.commands.values;

import edu.ubo.satellitebeacons.main.commands.exceptions.TypeException;
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
  public CharSequence pretty(int level) {
    return PrettyFormatterEnum.NB.format(value).toString();
  }
  
  @Override
  public Value<?> getAttribute(final String attribute) throws TypeException {
    return Value.UNDEFINED_VALUE;
  }

  protected final Number value;
}
