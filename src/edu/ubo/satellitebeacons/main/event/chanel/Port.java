package edu.ubo.satellitebeacons.main.event.chanel;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.MessageEvent;
import edu.ubo.satellitebeacons.main.event.emitter.Emitter;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.event.manager.EventManager;

public class Port<T> implements Emitter {

  public Port() {
    this.eventManager = new EventManager();
  }
  
  public void send(final T content) {
    this.eventManager.emitEvent(new MessageEvent<>(this, content));
  }
  
  @Override
  public <E extends EventObject> Listener<E> addEventListener(final Class<E> event, final Listener<E> l) {
    return this.eventManager.addEventListener(event, l);
  }
  
  @Override
  public <E extends EventObject> Listener<E> removeEventListener(final Class<E> event, final Listener<E> l) {
    return this.eventManager.removeEventListener(event, l);
  }
  
  protected final EventManager eventManager;
}
