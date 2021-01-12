package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.event.emitter.DestinationReachEmitter;

/**
 * Movement which emit a {@linkplain DestinationReachEmitter}.
 */
public interface DirectionalMovement extends Movement, DestinationReachEmitter {

}
