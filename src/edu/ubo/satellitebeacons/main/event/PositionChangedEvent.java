package edu.ubo.satellitebeacons.main.event;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.movable.Position;

public class PositionChangedEvent extends EventObject {

  public PositionChangedEvent(final Object source, final Position position) {
    super(source);
    this.position = position;
  }
  
  public Position getPosition() {
    return position;
  }

  protected final Position position;
}
