package edu.ubo.satellitebeacons.main.simulation.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import edu.ubo.graphicLayer.GRect;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.StartSyncEvent;
import edu.ubo.satellitebeacons.main.event.StopSyncEvent;

public class GMovable extends GRect {

  @Override
  public void draw(Graphics2D g) {
    super.draw(g);
    if (this.isSynchronising) {
      final Color c = g.getColor();
      final Stroke s = g.getStroke();
      final Rectangle bounds = this.getBounds();
      g.setColor(Color.ORANGE);
      g.setStroke(new BasicStroke(2));
      for (int i = 10; i < 150; i += 25) {
        g.drawOval(bounds.x - i, bounds.y - i, bounds.width + 2 * i, bounds.height + 2 * i);
      }
      g.setStroke(s);
      g.setColor(c);
    }
  }
  
  public void onPositionChangedEvent(final PositionChangedEvent event) {
    this.setPosition(new Point(event.getPosition().getX(), event.getPosition().getY()));
    this.repaint();
  }
  
  public void onStartSync(final StartSyncEvent event) {
    this.isSynchronising = true;
  }
  
  public void onStopSync(final StopSyncEvent event) {
    this.isSynchronising = false;
  }

  protected boolean isSynchronising = false;
}
