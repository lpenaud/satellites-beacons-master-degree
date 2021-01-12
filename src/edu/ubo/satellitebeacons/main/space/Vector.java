package edu.ubo.satellitebeacons.main.space;

/**
 * Represents a vector, a movement in 2d.
 */
public class Vector {

  /**
   * New vector with the given x and y coordinates.
   * @param x
   * @param y
   */
  public Vector(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get coordinate on x axis.
   * @return Coordinate on x axis.
   */
  public int getX() {
    return x;
  }

  /**
   * Get coordinate on y axis
   * @return Coordinate on y axis.
   */
  public int getY() {
    return y;
  }

  protected final int x;
  protected final int y;
}
