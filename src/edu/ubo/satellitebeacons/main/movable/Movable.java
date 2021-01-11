package edu.ubo.satellitebeacons.main.movable;

import java.util.Collection;
import java.util.EventObject;
import java.util.HashSet;
import edu.ubo.satellitebeacons.main.event.EventManager;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.emitter.PositionChangedEmitter;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.movable.movement.Movement;
import edu.ubo.satellitebeacons.main.observable.Observable;
import edu.ubo.satellitebeacons.main.observable.Observer;
import edu.ubo.satellitebeacons.main.space.Position;

public abstract class Movable implements Observable<Position>, PositionChangedEmitter {

  public Movable() {
    this.observers = new HashSet<>();
    this.listenerManager = new EventManager();
  }

  public Position getPosition() {
    return this.position;
  }

  public void setPosition(final Position position) {
    this.position = position;
  }

  @Override
  public void addObserver(Observer<Position> observer) {
    observers.add(observer);
  }

  @Override
  public void deleteObserver(Observer<Position> observer) {
    this.observers.remove(observer);
  }

  @Override
  public void deleteObservers() {
    this.observers.clear();
  }

  @Override
  public void notifyObservers(Position target) {
    observers.forEach(o -> o.update(target));
  }

  @Override
  public int countObservers() {
    return observers.size();
  }
  
  public void move() {
    this.movement.move(this);
    notifyObservers(position);
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
  protected final Collection<Observer<Position>> observers;
  protected Movement movement;
  protected Position position;
}
