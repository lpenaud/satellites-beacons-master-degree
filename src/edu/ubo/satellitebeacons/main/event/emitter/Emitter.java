package edu.ubo.satellitebeacons.main.event.emitter;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.listener.Listener;

public interface Emitter {
  void addEventListener(Class<? extends EventObject> event, Listener l);
}
