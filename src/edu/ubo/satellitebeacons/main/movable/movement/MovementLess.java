package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.movable.Movable;

public class MovementLess implements Movement {
  public static final Movement SINGLETON = new MovementLess(); 
  
  private MovementLess() {}

  @Override
  public void move(final Movable movable) {
    // Don't move
  }

}
