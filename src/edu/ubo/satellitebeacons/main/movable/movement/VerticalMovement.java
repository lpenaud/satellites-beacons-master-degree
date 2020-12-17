package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.movable.Movable;
import edu.ubo.satellitebeacons.main.space.Position;
import edu.ubo.satellitebeacons.main.space.SpaceFactory;

public class VerticalMovement implements Movement {
  
  public VerticalMovement(final int min, final int max) {
    this.min = min;
    this.max = max;
  }
  
  @Override
  public void move(final Movable movable) {
    final Position position;
    if (mount) {
      position = SpaceFactory.SINGLETON.nextY(movable.getPosition(), 5);
      if (position.getY() >= max) {
        mount = false;
      }
    } else {
      position = SpaceFactory.SINGLETON.nextY(movable.getPosition(), -5);
      if (position.getY() <= min) {
        mount = true;
      }
    }
    movable.setPosition(position);
  }

  protected final int min;
  protected final int max;
  protected boolean mount;
}
