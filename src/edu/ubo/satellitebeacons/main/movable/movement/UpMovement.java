package edu.ubo.satellitebeacons.main.movable.movement;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.event.manager.EventManager;
import edu.ubo.satellitebeacons.main.movable.Movable;
import edu.ubo.satellitebeacons.main.space.Vector;

public class UpMovement implements DirectionalMovement {
  
  public UpMovement(final int min, final int speed) {
    this.min = min;
    this.speed = speed;
    this.eventManager = new EventManager();
  }

  @Override
  public void move(Movable movable) {
    final var position = movable.getPosition().previousY(speed);
    movable.setPosition(position);
    if (position.getY() <= min) {
      this.emitMaxReach();
    }
  }

  @Override
  public void emitMaxReach() {
    this.eventManager.emitEvent(new DestinationReachEvent(this, new Vector(0, speed)));
  }

  @Override
  public <E extends EventObject> Listener<E> addEventListener(Class<E> event, Listener<E> l) {
    return this.eventManager.addEventListener(event, l);
  }
  
  @Override
  public <E extends EventObject> Listener<E> removeEventListener(Class<E> event, Listener<E> l) {
    return this.eventManager.removeEventListener(event, l);
  }
  
  protected final int min;
  protected final int speed;
  protected final EventManager eventManager;
}
