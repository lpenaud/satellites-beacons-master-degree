package edu.ubo.satellitebeacons.main.space;

import java.util.Objects;

public class Position {

  Position(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return new StringBuilder("Position{ x: ")
        .append(x)
        .append(", y: ")
        .append(y)
        .append(" }")
        .toString();
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (super.equals(obj)) {
      return true;
    }
    if (obj instanceof Position) {
      final var p = (Position) obj;
      return p.x == this.x && p.y == this.y;
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  protected final int x;
  protected final int y;

}
