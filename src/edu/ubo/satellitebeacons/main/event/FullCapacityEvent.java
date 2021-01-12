package edu.ubo.satellitebeacons.main.event;

import java.util.EventObject;

public class FullCapacityEvent extends EventObject {
  private static final long serialVersionUID = -4807637803997634615L;

  public FullCapacityEvent(final Object source) {
    super(source);
  }
}
