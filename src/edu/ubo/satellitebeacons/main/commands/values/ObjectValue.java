package edu.ubo.satellitebeacons.main.commands.values;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import edu.ubo.satellitebeacons.main.annotations.ScriptGetter;
import edu.ubo.satellitebeacons.main.commands.exceptions.TypeException;
import edu.ubo.satellitebeacons.main.utils.Utils;

public class ObjectValue implements Value<Object> {
	
	// Seconde posibilité : Créer les classes spécialisé Movable
	public static <T> Property<T> newProperty(final Object o, final Class<T> cl) {
		final var getter = cl.getMethods()[0];
		final var anno = getter.getAnnotation(ScriptGetter.class);
		final var setter = cl.getMethod(anno.setter(), getter.getReturnType());
		return new Property<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public T get() {
				return (T) getter.invoke(o);
			}
			
			@Override
			public void set(final T value) {
				setter.invoke(o, value);
			}
		};
	}
	
	// Why Function<ObjectValue, Value<?>> on properties ?
  public ObjectValue(final Object object) {
    this.value = object;
    
    this.properties = Stream.of(object.getClass().getMethods())
        .filter(m -> m.isAnnotationPresent(ScriptGetter.class))
        .collect(Collectors.toUnmodifiableMap(m -> m.getAnnotation(ScriptGetter.class).value(), m -> new Property<T>() {
		}))
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

  public Value<?> getAttribute(final String attribute) throws TypeException {
    final var property = this.properties.get(attribute);
    if (property == null) {
      return Value.UNDEFINED_VALUE;
    }
    return property.apply(this);
  }
  
  @Override
	public Value<?> setAttribute(final String attribute, final Value<?> value) throws TypeException {
	    this.properties.put(attribute, o -> value);
		return Value.super.setAttribute(attribute, value);
	}

  protected final Object value;
  protected final Map<String, Function<ObjectValue, Value<?>>> properties;
}
