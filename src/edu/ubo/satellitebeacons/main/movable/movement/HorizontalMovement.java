package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.movable.Movable;

public class HorizontalMovement implements Movement {
  
  @Override
  public void move(final Movable movable) {
    movable.setPosition(movable.getPosition().nextX(1));
  }

}
