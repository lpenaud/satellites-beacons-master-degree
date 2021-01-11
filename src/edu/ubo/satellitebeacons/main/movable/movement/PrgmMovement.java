package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.movable.Movable;

public class PrgmMovement implements Movement {
  
  public PrgmMovement(final Runnable task) {
    this.task = task;
  }

  @Override
  public void move(Movable movable) {
    this.task.run();    
  }
  
  protected final Runnable task;
}
