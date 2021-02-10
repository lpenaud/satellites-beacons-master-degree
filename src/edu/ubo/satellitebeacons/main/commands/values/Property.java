package edu.ubo.satellitebeacons.main.commands.values;

public interface Property<T> {
  T get();
  void set(T value);
}
