package edu.ubo.satellitebeacons.main.commands;

import java.util.Map;
import edu.ubo.satellitebeacons.main.commands.values.Value;

public interface Command<R> {  
  Value<R> call(Map<String, Object> args);
}
