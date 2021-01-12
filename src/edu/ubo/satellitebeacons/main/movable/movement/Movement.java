package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.movable.Movable;

/**
 * Represents a movement.
 */
public interface Movement {
  /**
   * Move a movable.
   * @param movable The movable to move.
   */
  void move(Movable movable);
}
