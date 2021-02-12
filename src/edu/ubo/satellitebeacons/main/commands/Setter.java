package edu.ubo.satellitebeacons.main.commands;

import java.util.Map;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.movable.movement.Movement;

public class Setter {

  public Movement setMovement(final Map<String, Object> args) {
    final var m = (Movement) args.get("m");
    ((Satellite) args.get("s")).setMovement(m);
    return m;
  }
  
}
