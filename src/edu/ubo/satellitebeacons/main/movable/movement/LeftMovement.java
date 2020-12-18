package edu.ubo.satellitebeacons.main.movable.movement;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.EventManager;
import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.movable.Movable;
import edu.ubo.satellitebeacons.main.space.Vector;

public class LeftMovement implements DirectionalMovement {

  public LeftMovement(final int min, final int speed) {
    this.min = min;
    this.speed = speed;
    this.eventManager = new EventManager();
  }

  @Override
  public void move(final Movable movable) {
    final var position = movable.getPosition().previousX(speed);
    movable.setPosition(position);
    if (position.getX() <= min) {
      this.emitMaxReach();
    }
  }

  @Override
  public <E extends EventObject> void addEventListener(Class<E> event, Listener<E> l) {
    this.eventManager.addEventListener(event, l);
  }

  @Override
  public void emitMaxReach() {
    this.eventManager.emitEvent(new DestinationReachEvent(this, new Vector(speed, 0)));
  }

  protected final int min;
  protected final int speed;
  protected final EventManager eventManager;
}
