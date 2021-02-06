package edu.ubo.satellitebeacons.main.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import edu.ubo.satellitebeacons.main.utils.PrettyFormatterEnum;

@Retention(RUNTIME)
@Target(METHOD)
public @interface ScriptGetter {
  String value();
  PrettyFormatterEnum[] formatter() default {};
}
