package edu.ubo.satellitebeacons.main.simulation.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.function.Consumer;
import edu.ubo.graphicLayer.GRect;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.StartSyncEvent;
import edu.ubo.satellitebeacons.main.event.StopSyncEvent;
import edu.ubo.satellitebeacons.main.utils.GraphicUtils;

/**
 * Basic graphic element which represents a movable.
 * @see GBeacon
 * @see GSatellite
 */
public class GMovable extends GRect {
  
  public GMovable() {
    this.draw = this::drawNothing;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void draw(final Graphics2D g) {
    super.draw(g);
    this.draw.accept(g);
  }
  
  /**
   * Position changed listener.
   * @param event
   */
  public void onPositionChangedEvent(final PositionChangedEvent event) {
    this.setPosition(GraphicUtils.positionToPoint(event.getPosition()));
    this.repaint();
  }
  
  /**
   * Synchronization between beacon and satellite is starting.
   * Start the synchronization animation.
   * @param event
   */
  public void onStartSync(final StartSyncEvent event) {
    this.draw = this::drawSyncAnim;
  }
  
  /**
   * Synchronization between beacon and satellite is stopping.
   * Stop the synchronization animation.
   * @param event
   */
  public void onStopSync(final StopSyncEvent event) {
    this.draw = this::drawNothing;
  }
  
  /**
   * Draw the synchronisation animation.
   * @see #draw
   * @param g
   */
  public void drawSyncAnim(final Graphics2D g) {
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
  
  /**
   * Draw none animation
   * @see #draw
   * @param g
   */
  public void drawNothing(final Graphics2D g) {
    // Draw just the necessary.
  }
  
  protected Consumer<Graphics2D> draw;
}
