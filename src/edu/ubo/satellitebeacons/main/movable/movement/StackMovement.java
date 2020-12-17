package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.listener.DestinationReachListener;
import edu.ubo.satellitebeacons.main.movable.Movable;

public class StackMovement implements Movement, DestinationReachListener {
  
  public StackMovement(final DirectionalMovement movement) {
    this.movements = new DirectionalMovement[1];
    this.setDirectionalMovement(1, movement);
    this.index = 0;
  }
  
  public StackMovement(final DirectionalMovement...movements) {
    this.movements = new DirectionalMovement[movements.length];
    for (int i = 0; i < movements.length; i++) {
      this.setDirectionalMovement(i, movements[i]);
    }
    this.index = 0;
  }

  @Override
  public void move(final Movable movable) {
    this.movements[index].move(movable);
  }
  
  @Override
  public void onMaxReachEvent(DestinationReachEvent event) {
    if (this.index >= this.movements.length) {
      this.index++;
    } else {
      this.index = 0;
    }
  }
  
  protected void setDirectionalMovement(final int i, final DirectionalMovement movement) {
    this.movements[i] = movement;
    movement.addEventListener(DestinationReachEvent.class, this::onMaxReachEvent);
  }
  
  protected final DirectionalMovement[] movements;
  protected int index;
}
