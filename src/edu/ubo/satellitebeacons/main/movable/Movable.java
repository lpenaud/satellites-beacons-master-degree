package edu.ubo.satellitebeacons.main.movable;

import edu.ubo.satellitebeacons.main.world.Position;

public interface Movable {
  Position getPosition();
  void setPosition(Position position);
}
