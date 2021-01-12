package edu.ubo.satellitebeacons.main.movable;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.emitter.PositionChangedEmitter;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.event.manager.EventManager;
import edu.ubo.satellitebeacons.main.movable.movement.Movement;
import edu.ubo.satellitebeacons.main.space.Position;

/**
 * @see Beacon
 * @see Satellite
 */
public abstract class Movable implements PositionChangedEmitter {

  /**
   * Create a new movable.
   */
  public Movable() {
    this.listenerManager = new EventManager();
  }

  /**
   * Get position of the movable.
   * @return Position of the movable.
   */
  public Position getPosition() {
    return this.position;
  }

  /**
   * Set position of the movable.
   * @param position The new position of the movable.
   */
  public void setPosition(final Position position) {
    this.position = position;
  }
  
  /**
   * Move the movable according to its movement.
   * @see Movement
   */
  public void move() {
    this.movement.move(this);
    emitPositionChanged();
  }

  /**
   * Get the movement of the movable.
   * @return The movement of the movable.
   */
  public Movement getMovement() {
    return movement;
  }
  
  /**
   * Set the movement of the movable.
   * @param movement The new movement to the movable.
   */
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
