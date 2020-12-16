package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.listener.DestinationReachListener;
import edu.ubo.satellitebeacons.main.movable.Movable;

public class HorizontalMouvement implements Movement, DestinationReachListener {
  
  public HorizontalMouvement(final int min, final int max, final int speed) {
    this.min = min;
    this.max = max;
    this.createMovement(speed);
  }
  
  @Override
  public void move(final Movable movable) {
    this.movement.move(movable);
  }

  @Override
  public void onMaxReachEvent(final DestinationReachEvent event) {
    this.createMovement(-event.getSpeed().getX());
  }
  
  protected void createMovement(final int speed) {
    movement = speed < 0 ? new LeftMovement(min, speed) : new RightMovement(max, speed);
    movement.addEventListener(DestinationReachEvent.class, this::onMaxReachEvent);
  }
  
  protected final int min;
  protected final int max;
  protected DirectionalMovement movement;
}
