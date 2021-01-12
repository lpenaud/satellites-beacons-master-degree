package edu.ubo.satellitebeacons.main.event.emitter;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.listener.Listener;

/**
 * Represents an object which can emit events.
 * @see EventObject 
 */
public interface Emitter {
  
  /**
   * Add a new event listener.
   * The listener will be call every time where the event is emitted.
   * @param <E> Any event based on {@linkplain EventObject}.
   * @param event Event that will be listened to.
   * @param l Listener to call when the event is emitted.
   * @return The listener if it is correctly registered otherwise <code>null</code>.
   */
  <E extends EventObject> Listener<E> addEventListener(Class<E> event, Listener<E> l);

  /**
   * Remove a listener.<br>
   * <b>Warning</b> this method is optional.
   * @param <E> Any event based on {@linkplain EventObject}.
   * @param event Event where the listener listen.
   * @param l Reference to the listener method.
   * @return The listener if it is correctly removed otherwise <code>null</code>.
   * @throws UnsupportedOperationException If the method is not implemented by the object.
   */
  default <E extends EventObject> Listener<E> removeEventListener(Class<E> event, Listener<E> l) {
    throw new UnsupportedOperationException();
  }
}
