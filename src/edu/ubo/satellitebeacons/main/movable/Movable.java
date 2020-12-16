package edu.ubo.satellitebeacons.main.movable;

import java.util.Collection;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.emitter.PositionChangedEmitter;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.event.listener.PositionChangedListener;
import edu.ubo.satellitebeacons.main.movable.movement.Movement;
import edu.ubo.satellitebeacons.main.observable.Observable;
import edu.ubo.satellitebeacons.main.observable.Observer;

public abstract class Movable implements Observable<Position>, PositionChangedEmitter {

  public Movable() {
    this.observers = new HashSet<>();
    this.map = new HashMap<>();
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
  
  @Override
  public void addEventListener(final Class<? extends EventObject> event, final Listener l) {
    //TODO: Add objet to register listener
    final var listeners = this.map.computeIfAbsent(event, k -> new HashSet<>());
    listeners.add(l);
  }
  
  @Override
  public void emitPositionChanged() {
    final var listeners = this.map.get(PositionChangedEvent.class);
    if (listeners == null) {
      return;
    }
    final var event = new PositionChangedEvent(this, this.position);
    listeners.forEach(l -> ((PositionChangedListener) l).onPositionChanged(event));
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

  protected final Map<Class<? extends EventObject>, Set<Listener>> map;
  protected final Collection<Observer<Position>> observers;
  protected Movement movement;
  protected Position position;
}
