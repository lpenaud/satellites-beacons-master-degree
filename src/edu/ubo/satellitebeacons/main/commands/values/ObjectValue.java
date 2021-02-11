package edu.ubo.satellitebeacons.main.commands.values;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import edu.ubo.satellitebeacons.main.annotations.ScriptGetter;
import edu.ubo.satellitebeacons.main.commands.Command;
import edu.ubo.satellitebeacons.main.commands.exceptions.TypeException;
import edu.ubo.satellitebeacons.main.utils.Utils;

public class ObjectValue implements Value<Object> {  
  public ObjectValue(final Object object) {
    this.value = object;
    this.properties = Stream.of(object.getClass().getMethods())
        .filter(m -> m.isAnnotationPresent(ScriptGetter.class)).collect(
            Collectors.toUnmodifiableMap(m -> m.getAnnotation(ScriptGetter.class).value(), m -> {
              final var anno = m.getAnnotation(ScriptGetter.class);
              try {
                final Value<?> wrapperInstance =
                    (Value<?>) anno.wrapper().getConstructors()[0].newInstance(m.invoke(object));
                return self -> wrapperInstance;
              } catch (Exception e) {
                return v -> Value.UNDEFINED;
              }
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
      return Value.UNDEFINED;
    }
    return property.apply(this);
  }

  @Override
  public Command<?> getCommand(String attribute) throws TypeException {
    return Value.super.getCommand(attribute);
  }

  protected final Object value;
  protected final Map<String, Function<ObjectValue, Value<?>>> properties;
}
