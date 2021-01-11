package edu.ubo.satellitebeacons.main.event;

import java.util.EventObject;

public class SyncEvent extends EventObject {
  private static final long serialVersionUID = -5459951130024888468L;

  public SyncEvent(final Object source) {
    super(source);
  }
}
