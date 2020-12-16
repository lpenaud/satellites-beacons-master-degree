package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.movable.Movable;
import edu.ubo.satellitebeacons.main.movable.Position;

public class HorizontalMovement implements Movement {
  
  public HorizontalMovement(final int min, final int max) {
    this.min = min;
    this.max = max;
    this.leftToRight = true;
  }
  
  @Override
  public void move(final Movable movable) {
    final Position position;
    if (leftToRight) {
      position = movable.getPosition().nextX(10);
      if (position.getX() >= max) {
        leftToRight = false;        
      }
    } else {
      position = movable.getPosition().nextX(-10);
      if (position.getX() <= min) {
        leftToRight = true;
      }
    }
    movable.setPosition(position);
  }
  
  protected final int min;
  protected final int max;
  protected boolean leftToRight;

}
