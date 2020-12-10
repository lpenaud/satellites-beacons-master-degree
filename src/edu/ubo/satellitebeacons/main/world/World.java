package edu.ubo.satellitebeacons.main.world;

public class World {

  public World(final int width, final int height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height have to be superior than 0");
    }
    this.matrix = new Position[width][height];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        this.matrix[x][y] = new Position(x, y);
      }
    }
  }

  /**
   * Get a position of the matrix from a position
   * 
   * If the given position are out of bound :
   * <ul>
   *    <li>On x axis: Do the difference with matrix width</li>
   *    <li>On y axis: Do the difference with matrix height</li>
   * </ul>
   * 
   * @param position Try to get this position
   * @return Position from the matrix
   */
  public Position getPosition(final Position position) {
    if (position.x > this.getWidth()) {
      position.x -= this.getWidth();
    }
    if (position.y > this.getHeight()) {
      position.y -= this.getHeight();
    }
    return this.matrix[position.x][position.y];
  }

  public int getWidth() {
    return this.matrix.length;
  }

  public int getHeight() {
    return this.matrix[0].length;
  }

  final Position[][] matrix;

}
