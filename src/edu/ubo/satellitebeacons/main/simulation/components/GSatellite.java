package edu.ubo.satellitebeacons.main.simulation.components;

import java.io.IOException;
import edu.ubo.satellitebeacons.main.utils.GConstants;
import edu.ubo.satellitebeacons.main.utils.GraphicUtils;

/**
 * Graphic element which represents a satellite.
 * @see GMovable
 */
public class GSatellite extends GMovable {
  
  public GSatellite() {
    try {
      GraphicUtils.setImage(this, GConstants.SATELLITE_PICTURE);
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
