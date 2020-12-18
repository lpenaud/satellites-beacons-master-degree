package edu.ubo.satellitebeacons.main.event;

import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import edu.ubo.satellitebeacons.main.event.emitter.Emitter;
import edu.ubo.satellitebeacons.main.event.listener.Listener;

public class EventManager implements Emitter {
  
  public EventManager() {
    this.map = new HashMap<>();
  }
  
  @Override
  public <E extends EventObject> void addEventListener(final Class<E> event, final Listener<E> l) {
    final var listeners = this.map.computeIfAbsent(event, v -> new HashSet<>());
    listeners.add(l);
  }
  
  @Override
  public <E extends EventObject> void removeEventListener(final Class<E> event, final Listener<E> l) {
    final var listeners = this.map.get(event);
    if (listeners != null) {
      listeners.remove(l);
    }
  }
  
  @SuppressWarnings("unchecked")
  public <E extends EventObject> void emitEvent(final E event) {
    final var listeners = this.map.get(event.getClass());
    if (listeners == null) {
      return;
    }
    listeners.forEach(l -> ((Listener<E>) l).onEvent(event));
  }
  
  protected final Map<Class<? extends EventObject>, Set<Listener<?>>> map;
}
