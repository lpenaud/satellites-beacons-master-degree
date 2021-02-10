package edu.ubo.satellitebeacons.main.commands.values;

import java.util.Map;
import java.util.stream.Collectors;
import edu.ubo.satellitebeacons.main.commands.exceptions.TypeException;
import edu.ubo.satellitebeacons.main.utils.Utils;

public class MapValue implements Value<Map<String, Value<?>>> {

  public MapValue(final String name, final Map<String, Value<?>> value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public Map<String, Value<?>> getValue() {
    return value;
  }

  @Override
  public CharSequence pretty(final int level) {
    return new StringBuilder(Utils.printTab(level)).append(name).append(" {\n")
        .append(
            value.entrySet().stream().map(entry -> prettyPrint(entry.getKey(), entry.getValue(), level + 1))
                .collect(Collectors.joining(",\n")))
        .append("\n}").toString();
  }

  protected CharSequence prettyPrint(final String key, final Value<?> value, final int level) {
    return new StringBuilder(Utils.printTab(level)).append(key).append(": ")
        .append(value == this ? "[Ref Circular]" : value.pretty(level + 1));
  }

  @Override
  public Value<?> getAttribute(String attribute) throws TypeException {
    return this.value.getOrDefault(attribute, Value.UNDEFINED_VALUE);
  }

  protected final Map<String, Value<?>> value;
  protected final String name;
}
