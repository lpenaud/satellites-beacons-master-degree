package edu.ubo.satellitebeacons.main.utils;

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
  
  public static StringBuilder addSpaces(final StringBuilder builder, final int nbSpace) {
    for (int i = 0; i < nbSpace; i++) {
      builder.append(" ");
    }
    return builder;
  }
  
  public static int parseIntOrDefault(final String s, final int d) {
    try {
      return Integer.parseInt(s);
    } catch (final NumberFormatException e) {
      return d;
    }
  }

  private Utils() {}
}
