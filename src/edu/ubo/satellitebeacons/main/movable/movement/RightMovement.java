package edu.ubo.satellitebeacons.main.movable.movement;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.event.manager.EventManager;
import edu.ubo.satellitebeacons.main.movable.Movable;
import edu.ubo.satellitebeacons.main.space.Vector;

public class RightMovement implements DirectionalMovement {

  public RightMovement(final int max, final int speed) {
    this.max = max;
    this.speed = speed;
    this.eventManager = new EventManager();
  }

  @Override
  public void move(final Movable movable) {
    final var position = movable.getPosition().nextX(speed);
    movable.setPosition(position);
    if (position.getX() >= max) {
      this.emitMaxReach();
    }
  }

  @Override
  public <E extends EventObject> Listener<E> addEventListener(Class<E> event, Listener<E> l) {
    return this.eventManager.addEventListener(event, l);
  }

  @Override
  public void emitMaxReach() {
    this.eventManager.emitEvent(new DestinationReachEvent(this, new Vector(speed, 0)));
  }

  protected final int max;
  protected final int speed;
  protected final EventManager eventManager;
}
