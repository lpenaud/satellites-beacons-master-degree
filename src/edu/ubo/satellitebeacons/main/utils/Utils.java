package edu.ubo.satellitebeacons.main.utils;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import edu.ubo.satellitebeacons.main.annotations.ScriptClass;
import edu.ubo.satellitebeacons.main.annotations.ScriptGetter;
import edu.ubo.satellitebeacons.main.space.Position;

public final class Utils {
  public static final int TAB_LEN = 2;
  public static final Random RANDOM = new Random();

  /**
   * Get satellite speed according to its position.
   * 
   * @param p Position to the satellite.
   * @return {@linkplain Position#getY()} * {@linkplain Constants#SATELLITE_SPEED_FACTOR} +
   *         {@linkplain Constants#SATELLITE_MIN_SPEED}
   */
  public static int getSatelliteSpeed(final Position p) {
    return Math.round(p.getY() * Constants.SATELLITE_SPEED_FACTOR) + Constants.SATELLITE_MIN_SPEED;
  }

  public static StringBuilder addSpaces(final StringBuilder builder, final int nbSpace) {
    for (int i = 0; i < nbSpace; i++) {
      builder.append(" ");
    }
    return builder;
  }
  
  public static int parseIntOrDefault(final Object o, final int d) {
    try {
      return parseIntOrDefault(o.toString(), d);      
    } catch (Exception e) {
      return d;
    }
  }

  public static int parseIntOrDefault(final String s, final int d) {
    try {
      return Integer.parseInt(s);
    } catch (final NumberFormatException e) {
      return d;
    }
  }

  public static CharSequence prettyPrint(final Object o) {
    return prettyPrint(o, 0);
  }

  public static CharSequence prettyPrint(final Object o, final int level) {
    if (o == null) {
      return "null";
    }
    final var anno = o.getClass().getAnnotation(ScriptClass.class);
    final var builder =
        new StringBuilder(anno != null ? anno.value() : o.getClass().getSimpleName())
            .append(" {\n");
    final var res =
        Stream.of(o.getClass().getMethods()).filter(m -> m.isAnnotationPresent(ScriptGetter.class))
            .map(m -> prettyPrint(o, m, level + 1)).collect(Collectors.joining(",\n"));
    return builder.append(res).append('\n').append(printTab(level)).append('}');
  }

  public static CharSequence prettyPrint(final Object o, final Method method, final int level) {
    final var anno = method.getAnnotation(ScriptGetter.class);
    final var builder = new StringBuilder(printTab(level)).append(anno.value()).append(": ");
    try {
      final var value = method.invoke(o);
      if (value == null) {
        return builder.append(value);
      }
      if (anno.formatter().length == 0) {
        return builder.append(prettyPrint(value, level + 1));
      }
      return builder.append(anno.formatter()[0].format(value));
    } catch (final IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      return null;
    }
  }

  public static CharSequence printTab(int nb) {
    final var builder = new StringBuilder(nb);
    while ((nb--) > 0) {
      builder.append(" ");
    }
    return builder;
  }

  public static String join(final Object... objects) {
    return join(",", objects);
  }

  public static String join(final CharSequence sep, final Object... objects) {
    return Stream.of(objects).collect(new CollectorsUtils<>(StringBuilder::new,
        StringBuilder::append, (r1, r2) -> r1.append(sep).append(r2), StringBuilder::toString));
  }
  
  @SafeVarargs
  public static <T> T getRandom(final T...ts) {
    return ts[Math.abs(RANDOM.nextInt()) % ts.length];
  }
  
  public static int parseIntOrRandom(final String s, final Integer...intergers) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return getRandom(intergers);
    }
  }
  
  public static int parseIntOrRandom(final Object o, final Integer...intergers) {
    try {
      return parseIntOrRandom(o.toString(), intergers);      
    } catch (Exception e) {
      return getRandom(intergers);
    }
  }

  private Utils() {}
}
