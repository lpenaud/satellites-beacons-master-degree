package edu.ubo.satellitebeacons.main.commands.values;

import edu.ubo.satellitebeacons.main.utils.PrettyFormatterEnum;

public class StringValue implements Value<String> {

  public StringValue(final String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return PrettyFormatterEnum.STRING.format(value).toString();
  }

  protected final String value;
}
