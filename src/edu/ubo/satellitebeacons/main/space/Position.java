package edu.ubo.satellitebeacons.main.space;

import edu.ubo.satellitebeacons.main.annotations.ScriptGetter;
import edu.ubo.satellitebeacons.main.commands.values.NumberValue;
import edu.ubo.satellitebeacons.main.utils.PrettyFormatterEnum;

/**
 * Represents a position in 2d.
 */
public class Position {

  /**
   * Create a new 2d position.
   * @param x Coordinated on the x-axis 
   * @param y Coordinated on the y-axis 
   */
  public Position(final int x, final int y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Add a vector to the position.
   * @param vector 2d vector with x and y coordinates.
   * @return New position which represents the result of adding of the vector.
   */
  public Position next(final Vector vector) {
    return new Position(x + vector.x, y + vector.y);
  }

  /**
   * Add a int on the x axis.
   * @param x Int to add to the current position.
   * @return New position which represents the result of adding of the int on x axis. 
   */
  public Position nextX(final int x) {
    return new Position(this.x + x, this.y);
  }

  /**
   * Add a int on the y axis.
   * @param y Int to add to the current position.
   * @return New position which represents the result of adding of the int on y axis. 
   */
  public Position nextY(final int y) {
    return new Position(this.x, this.y + y);
  }
  
  /**
   * Remove a int on the x axis. 
   * @param x Int to remove to the current position.
   * @return New position which represents the result of removing of the int on x axis. 
   */
  public Position previousX(final int x) {
    return new Position(this.x - x, y);
  }
  
  /**
   * Remove a int on the y axis. 
   * @param y Int to remove to the current position.
   * @return New position which represents the result of removing of the vector. 
   */
  public Position previousY(final int y) {
    return new Position(this.x, this.y - y);
  }
  
  /**
   * Remove a vector to the position.
   * @param vector 2d vector with x and y coordinates.
   * @return New position which represents the result of removing of the int on y axis. 
   */
  public Position previous(final Vector vector) {
    return new Position(this.x - vector.x, this.y - vector.y);
  }

  /**
   * Get x coordinate.
   * @return x coordinate.
   */
  @ScriptGetter(value = "x", formatter = PrettyFormatterEnum.NB, wrapper = NumberValue.class)
  public Integer getX() {
    return x;
  }
  
  public void setX(int x) {
	this.x = x;
}

  /**
   * Get y coordinate.
   * @return y coordinate.
   */
  @ScriptGetter(value = "y", formatter = PrettyFormatterEnum.NB, wrapper = NumberValue.class)
  public Integer getY() {
    return y;
  }
  
  public void setY(int y) {
	this.y = y;
}

  @Override
  public String toString() {
    return new StringBuilder(super.toString())
        .append("{ x: ")
        .append(x)
        .append(", y: ")
        .append(y)
        .append(" }")
        .toString();
  }

  protected int x;
  protected int y;

}
