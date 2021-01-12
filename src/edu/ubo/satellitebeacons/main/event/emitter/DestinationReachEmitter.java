package edu.ubo.satellitebeacons.main.event.emitter;

public interface DestinationReachEmitter extends Emitter {
  default void emitMaxReach() {
    throw new UnsupportedOperationException();
  }
}
