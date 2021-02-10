package edu.ubo.satellitebeacons.main.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import edu.ubo.satellitebeacons.main.commands.values.ObjectValue;
import edu.ubo.satellitebeacons.main.commands.values.Value;

@Retention(RUNTIME)
@Target(METHOD)
public @interface ScriptSetter {
  String value();
  Class<? extends Value<?>> wrapper() default ObjectValue.class;
}
