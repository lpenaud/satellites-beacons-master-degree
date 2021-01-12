package edu.ubo.satellitebeacons.main.simulation.components;

import java.io.IOException;
import edu.ubo.satellitebeacons.main.simulation.utils.GConstants;
import edu.ubo.satellitebeacons.main.simulation.utils.GraphicUtils;

/**
 * Graphic element which represents a beacon
 * @see GMovable
 */
public class GBeacon extends GMovable {

  public GBeacon() {
    try {
      GraphicUtils.setImage(this, GConstants.BEACON_PICTURE);
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

}
