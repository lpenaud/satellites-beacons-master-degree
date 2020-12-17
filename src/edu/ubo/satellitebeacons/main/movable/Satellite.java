package edu.ubo.satellitebeacons.main.movable;

import java.util.ArrayList;
import java.util.List;
import edu.ubo.satellitebeacons.main.space.Position;

public class Satellite extends Movable {

  public Satellite(final Position position, final List<Movable> movables) {
    this.position = position;
    this.movables = movables;
  }
  
  public Satellite(final Position position) {
    this(position, new ArrayList<>());
  }
  
  protected final List<Movable> movables;

}
