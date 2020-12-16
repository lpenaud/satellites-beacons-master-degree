package edu.ubo.satellitebeacons.main.event.listener;

import java.util.EventObject;

public interface Listener<E extends EventObject> {
  void onEvent(E event);
}
