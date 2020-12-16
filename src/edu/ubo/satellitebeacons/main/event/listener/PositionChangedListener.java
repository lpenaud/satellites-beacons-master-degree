package edu.ubo.satellitebeacons.main.event.listener;

import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;

public interface PositionChangedListener extends Listener {
  void onPositionChanged(final PositionChangedEvent event);
}
