package edu.ubo.satellitebeacons.main.simulation.components;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import edu.ubo.graphicLayer.GImage;

public class GSatellite extends GMovable {
  
  public GSatellite() {
    final File file = new File("satellite.png");
    this.withoutBorder();
    this.withoutBackground();
    try {
      final BufferedImage img = ImageIO.read(file);
      this.addElement(new GImage(img));
      this.setDimension(new Dimension(img.getWidth(), img.getHeight()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
