package edu.ubo.satellitebeacons.main.simulation.utils;

import edu.ubo.satellitebeacons.main.space.Position;

public final class Utils {
  /**
   * Get satellite speed according to its position.
   * @param p Position to the satellite.
   * @return {@linkplain Position#getY()} * {@linkplain Constants#SATELLITE_SPEED_FACTOR} + {@linkplain Constants#SATELLITE_MIN_SPEED}
   */
  public static int getSatelliteSpeed(final Position p) {
    return Math.round(p.getY() * Constants.SATELLITE_SPEED_FACTOR) + Constants.SATELLITE_MIN_SPEED;
  }

  private Utils() {}
}
