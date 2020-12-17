package edu.ubo.satellitebeacons.main.simulation.components;

import java.awt.Dimension;
import java.awt.Point;
import edu.ubo.graphicLayer.GRect;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.listener.PositionChangedListener;
import edu.ubo.satellitebeacons.main.observable.Observer;
import edu.ubo.satellitebeacons.main.simulation.utils.GraphicUtils;
import edu.ubo.satellitebeacons.main.space.Position;

public class GBeacon extends GRect implements Observer<Position>, PositionChangedListener {

  public GBeacon() {
    this.setColor(GraphicUtils.getRandomColor());
    this.setDimension(new Dimension(30, 30));
  }

  @Override
  public void update(final Position target) {
    this.setPosition(new Point(target.getX(), target.getY()));
    this.repaint();
  }

  @Override
  public void onPositionChangedEvent(final PositionChangedEvent event) {
    this.setPosition(new Point(event.getPosition().getX(), event.getPosition().getY()));
    this.repaint();
  }

}
