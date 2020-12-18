package edu.ubo.satellitebeacons.main.event.emitter;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.listener.Listener;

public interface Emitter {
  <E extends EventObject> void addEventListener(Class<E> event, Listener<E> l);

  default <E extends EventObject> void removeEventListener(Class<E> event, Listener<E> l) {
    throw new UnsupportedOperationException();
  }
}
