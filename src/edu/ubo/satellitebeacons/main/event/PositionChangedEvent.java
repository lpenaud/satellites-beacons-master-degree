package edu.ubo.satellitebeacons.main.event;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.movable.Position;

public class PositionChangedEvent extends EventObject {
  private static final long serialVersionUID = 3681873409717434683L;

  public PositionChangedEvent(final Object source, final Position position) {
    super(source);
    this.position = position;
  }
  
  public Position getPosition() {
    return position;
  }

  protected final transient Position position;
}
