package edu.ubo.satellitebeacons.main.commands;

import java.util.Map;

public interface Command<R> {
  R call(Map<String, Object> args);
}
