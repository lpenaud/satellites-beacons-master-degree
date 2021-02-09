package edu.ubo.satellitebeacons.main.commands.values;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import edu.ubo.satellitebeacons.main.annotations.ScriptGetter;
import edu.ubo.satellitebeacons.main.commands.exceptions.TypeException;
import edu.ubo.satellitebeacons.main.utils.Utils;

public class ObjectValue implements Value<Object> {
  public ObjectValue(final Object object) {
    this.value = object;
    this.properties = Stream.of(object.getClass().getMethods())
        .filter(m -> m.isAnnotationPresent(ScriptGetter.class))
        .collect(Collectors.toUnmodifiableMap(m -> m.getAnnotation(ScriptGetter.class).value(),
            m -> (final ObjectValue wrapper) -> {
              final var anno = m.getAnnotation(ScriptGetter.class);
              try {
                return anno.wrapper().getConstructor(m.getReturnType())
                    .newInstance(m.invoke(wrapper.value));
              } catch (Exception e) {
                // Not reachable
              }
              return Value.UNDEFINED_VALUE;
            }));
  }

  @Override
  public Object getValue() {
    return value;
  }

  @Override
  public CharSequence pretty(final int level) {
    return Utils.prettyPrint(value, level);
  }

  public Value<?> getProperty(final String attribute) throws TypeException {
    final var property = this.properties.get(attribute);
    if (property == null) {
      return Value.UNDEFINED_VALUE;
    }
    return property.apply(this);
  }

  protected final Object value;
  protected final Map<String, Function<ObjectValue, Value<?>>> properties;
}
