package edu.ubo.satellitebeacons.main.movable;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.emitter.PositionChangedEmitter;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.event.manager.EventManager;
import edu.ubo.satellitebeacons.main.movable.movement.Movement;
import edu.ubo.satellitebeacons.main.space.Position;

public abstract class Movable implements PositionChangedEmitter {

  public Movable() {
    this.listenerManager = new EventManager();
  }

  public Position getPosition() {
    return this.position;
  }

  public void setPosition(final Position position) {
    this.position = position;
  }
  
  public void move() {
    this.movement.move(this);
    emitPositionChanged();
  }

  public Movement getMovement() {
    return movement;
  }
  
  public void setMovement(Movement movement) {
    this.movement = movement;
  }

  @Override
  public <E extends EventObject> Listener<E> addEventListener(Class<E> event, Listener<E> l) {
    return listenerManager.addEventListener(event, l);
  }

  @Override
  public <E extends EventObject> Listener<E> removeEventListener(Class<E> event, Listener<E> l) {
    return listenerManager.removeEventListener(event, l);
  }
  
  @Override
  public void emitPositionChanged() {
    this.listenerManager.emitEvent(new PositionChangedEvent(this, position));
  }

  protected final EventManager listenerManager;
  protected Movement movement;
  protected Position position;
}
