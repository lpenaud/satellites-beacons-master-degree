package edu.ubo.satellitebeacons.main.event.listener;

import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;

public interface DestinationReachListener {
  void onMaxReachEvent(DestinationReachEvent event);
}
