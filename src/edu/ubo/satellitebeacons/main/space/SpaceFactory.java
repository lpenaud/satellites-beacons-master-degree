package edu.ubo.satellitebeacons.main.space;

import java.util.ArrayList;
import java.util.List;

public class SpaceFactory {

  public static final SpaceFactory SINGLETON = new SpaceFactory();

  public SpaceFactory() {
    this.positions = new ArrayList<>();
  }

  public Position getPosition(final int x, final int y) {
    final var p = new Position(x, y);
    final var i = this.positions.indexOf(p);
    if (i == -1) {
      this.positions.add(p);
      return p;
    }
    return this.positions.get(i);
  }

  public Position nextY(final Position position, final int y) {
    return getPosition(position.x, position.y + y);
  }

  public Position nextX(final Position position, final int x) {
    return getPosition(position.x + x, position.y);
  }

  public Position next(final Position position, final Vector vector) {
    return new Position(position.x + vector.x, position.y + vector.y);
  }

  protected final List<Position> positions;
}
