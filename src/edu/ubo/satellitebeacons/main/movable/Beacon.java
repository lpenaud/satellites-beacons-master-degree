package edu.ubo.satellitebeacons.main.movable;

import edu.ubo.satellitebeacons.main.world.Position;
import edu.ubo.satellitebeacons.main.world.World;

public class Beacon implements Movable {

  public Beacon(final World world, final Position position) {
    this.world = world;
    this.position = position;
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public void setPosition(Position position) {
    this.position = this.world.getPosition(position);
  }

  protected final World world;
  protected Position position;

}
