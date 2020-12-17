package edu.ubo.satellitebeacons.main.simulation.utils;

import java.awt.Color;
import java.util.Random;

public class GraphicUtils {
  
  private static Random RANDOM = new Random();
  
  public static Color getRandomColor() {
    return new Color(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
  }
  
  private GraphicUtils() {}

}
