package edu.ubo.satellitebeacons.main.utils;

/**
 * Simulation constants.
 */
public final class Constants {
  
  /**
   * Sea level on y axis.
   */
  public static final int SEA_LEVEL = 300;
  
  /**
   * Minimum detection distance between a beacon and a satellite.
   */
  public static final int MIN_RADIUS = -5;
  
  /**
   * Maximum detection distance between a beacon and a satellite.
   */
  public static final int MAX_RADIUS = 5;
  
  /**
   * Beacon speed.
   */
  public static final int BEACON_SPEED = 5;

  /**
   * Satellite minium speed.
   * @see Utils#getSatelliteSpeed(edu.ubo.satellitebeacons.main.space.Position)
   */
  public static final int SATELLITE_MIN_SPEED = 10;

  /**
   * Satellite speed factor.
   * Useful to calculate satellite speed according to its position on y-axis.
   * @see Utils#getSatelliteSpeed(edu.ubo.satellitebeacons.main.space.Position)
   */
  public static final float SATELLITE_SPEED_FACTOR = 1E-2f;
  
  /**
   * Buffer size in the transfers.
   */
  public static final int BUFFER_SIZE = 10;

  private Constants() {}
  
}
