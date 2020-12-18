package edu.ubo.satellitebeacons.main.event.listener;

import edu.ubo.satellitebeacons.main.event.MessageEvent;

public interface ReveiveMessageListener<T> {
  void onMessage(final MessageEvent<T> event);
}
