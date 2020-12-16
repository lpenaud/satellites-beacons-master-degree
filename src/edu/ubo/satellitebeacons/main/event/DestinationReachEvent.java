package edu.ubo.satellitebeacons.main.event;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.space.Vector;

public class DestinationReachEvent extends EventObject {
  private static final long serialVersionUID = 6241998659081445567L;

  public DestinationReachEvent(final Object source, final Vector speed) {
    super(source);
    this.speed = speed;
  }
  
  public Vector getSpeed() {
    return speed;
  }
  
  protected final Vector speed;
}
