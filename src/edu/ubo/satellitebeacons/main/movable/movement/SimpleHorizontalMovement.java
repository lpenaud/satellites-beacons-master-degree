package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.annotations.ScriptClass;
import edu.ubo.satellitebeacons.main.movable.Movable;

@ScriptClass("SatelliteMovement")
public class SimpleHorizontalMovement implements Movement {  
  public SimpleHorizontalMovement(final int min, final int max, final int speed) {
    this.min = min;
    this.max = max;
    this.speed = speed;
    this.movement = this::moveRight;
  }

  @Override
  public void move(final Movable movable) {
    this.movement.move(movable);
  }
  
  public void moveLeft(final Movable movable) {
    var position = movable.getPosition().previousX(speed);
    if (position.getX() <= min) {
      movement = this::moveRight;
    }
    movable.setPosition(position);
  }
  
  public void moveRight(final Movable movable) {
    var position = movable.getPosition().nextX(speed);
    if (position.getX() >= max) {
      movement = this::moveLeft;
    }
    movable.setPosition(position);
  }
  
  protected final int min;
  protected final int max;
  protected final int speed;
  protected Movement movement;
}
