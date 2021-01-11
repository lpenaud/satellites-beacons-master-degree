package edu.ubo.satellitebeacons.main.event;

import java.util.EventObject;

public class StartSyncEvent extends EventObject {
  private static final long serialVersionUID = -5459951130024888468L;

  public StartSyncEvent(final Object source) {
    super(source);
  }
}
