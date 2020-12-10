package edu.ubo.satellitebeacons.main.world;

public class Position {

  public Position(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public Position nextX(final int x) {
    return new Position(this.x + x, this.y);
  }

  public Position nextY(final int y) {
    return new Position(this.x, this.y + y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  protected int x;
  protected int y;

}
