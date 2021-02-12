package edu.ubo.satellitebeacons.main.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import edu.ubo.graphicLayer.GImage;
import edu.ubo.graphicLayer.GRect;
import edu.ubo.satellitebeacons.main.space.Position;

/**
 * Some graphic utils methods.
 */
public final class GraphicUtils {
  
  /**
   * Add image to a rectangle
   * Set the rectangle dimension according to the image.
   * @param element Rectangle 
   * @param fileName Filename of the image.
   * @throws IOException if an error occurs during reading or when notable to create required ImageInputStream.
   */
  public static void setImage(final GRect element, final String fileName) throws IOException {
    final var img = ImageIO.read(new File(fileName));
    element.withoutBorder();
    element.withoutBackground();
    element.addElement(new GImage(img));
    element.setDimension(new Dimension(img.getWidth(), img.getHeight()));
  }
  
  public static Point positionToPoint(final Position position) {
    return new Point(position.getX(), position.getY());
  }
  
  private GraphicUtils() {}

}
