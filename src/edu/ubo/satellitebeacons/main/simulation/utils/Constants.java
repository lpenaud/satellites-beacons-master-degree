package edu.ubo.satellitebeacons.main.simulation.utils;

/**
 * Simulation constants.
 */
public final class Constants {
  
  /**
   * Sea level.
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
   * Buffer size in the transfers.
   */
  public static final int BUFFER_SIZE = 10;

  private Constants() {}
  
}
