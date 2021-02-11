package edu.ubo.satellitebeacons.main.commands.values;

import java.util.Map;
import java.util.function.Function;
import edu.ubo.satellitebeacons.main.commands.exceptions.TypeException;
import edu.ubo.satellitebeacons.main.utils.PrettyFormatterEnum;

public class StringValue implements Value<String> {
  public static final Map<String, Function<StringValue, Value<?>>> PROPERTIES =
      Map.of("length", StringValue::lengthValue);

  public StringValue(final String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public CharSequence pretty(int level) {
    return PrettyFormatterEnum.STRING.format(value);
  }

  @Override
  public Value<?> getProperty(final String attribute) throws TypeException {
    final var property = PROPERTIES.get(attribute);
    if (property == null) {
      return Value.UNDEFINED;
    }
    return property.apply(this);
  }

  public Value<Number> lengthValue() {
    return new NumberValue(value.length());
  }

  protected final String value;
}
