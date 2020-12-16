package edu.ubo.satellitebeacons.main.space;

public class Vector {
  
  public Vector(final int x, final int y) {
    this.x = x;
    this.y = y;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  protected final int x;
  protected final int y;
}
