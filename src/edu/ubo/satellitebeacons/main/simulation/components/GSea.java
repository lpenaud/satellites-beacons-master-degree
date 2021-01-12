package edu.ubo.satellitebeacons.main.simulation.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import edu.ubo.graphicLayer.GRect;

/**
 * Graphic element which represents the sea.
 */
public class GSea extends GRect {
  
  public GSea() {
    this.setDimension(new Dimension(800, 300));
    this.setPosition(new Point(0, 300));
    this.setColor(new Color(51, 173, 255));
    this.setBorderColor(this.color);
  }

}
