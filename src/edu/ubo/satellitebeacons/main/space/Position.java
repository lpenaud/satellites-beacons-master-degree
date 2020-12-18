package edu.ubo.satellitebeacons.main.space;

public class Position {

  public Position(final int x, final int y) {
    this.x = x;
    this.y = y;
  }
  
  public Position next(final Vector vector) {
    return new Position(x + vector.x, y + vector.y);
  }

  public Position nextX(final int x) {
    return new Position(this.x + x, this.y);
  }

  public Position nextY(final int y) {
    return new Position(this.x, this.y + y);
  }
  
  public Position previousX(final int x) {
    return new Position(this.x - x, y);
  }
  
  public Position previousY(final int y) {
    return new Position(this.x, this.y - y);
  }
  
  public Position previous(final Vector vector) {
    return new Position(this.x - vector.x, this.y - vector.y);
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

  protected int x;
  protected int y;

}
