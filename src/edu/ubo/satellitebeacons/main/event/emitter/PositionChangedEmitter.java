package edu.ubo.satellitebeacons.main.event.emitter;

public interface PositionChangedEmitter extends Emitter {
  default void emitPositionChanged() {
    throw new UnsupportedOperationException();
  }
}
