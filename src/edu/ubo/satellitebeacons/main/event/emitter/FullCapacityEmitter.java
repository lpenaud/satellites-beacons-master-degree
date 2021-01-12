package edu.ubo.satellitebeacons.main.event.emitter;

public interface FullCapacityEmitter extends Emitter {
  default void emitFullCapacity() {
    throw new UnsupportedOperationException();
  };
}
