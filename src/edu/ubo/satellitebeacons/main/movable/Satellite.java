package edu.ubo.satellitebeacons.main.movable;

import edu.ubo.satellitebeacons.main.annotations.ScriptClass;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.StartSyncEvent;
import edu.ubo.satellitebeacons.main.event.StopSyncEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.movable.movement.Movement;
import edu.ubo.satellitebeacons.main.movable.movement.PrgmMovement;
import edu.ubo.satellitebeacons.main.utils.Constants;
import edu.ubo.satellitebeacons.main.space.Position;

/**
 * Simulate the satellite behavior.
 */
@ScriptClass("Satellite")
public class Satellite extends Movable {

  /**
   * Create a new satellite with the given position and a communicate port.
   * 
   * @param position Depart position of the satellite.
   * @param port Communicate port to send its position.
   */
  public Satellite(final Position position, final Port<Satellite> port) {
    this.position = position;
    this.port = port;
    this.listenerManager.addEventListener(PositionChangedEvent.class, this::onPositionChangedEvent);
    this.listenerManager.addEventListener(StopSyncEvent.class, this::onStopEvent);
  }

  /**
   * {@linkplain PositionChangedEvent} listener.
   * 
   * @param event
   */
  public void onPositionChangedEvent(final PositionChangedEvent event) {
    this.port.send(this);
  }

  /**
   * Bacon and satellite communication.
   * 
   * @param beacon Beacon with the full memory.
   */
  public void receive(final Beacon beacon) {
    this.listenerManager.emitEvent(new StartSyncEvent(this));
    beacon.listenerManager.emitEvent(new StartSyncEvent(beacon));
    this.oldMovement = this.movement;
    this.movement = new PrgmMovement(() -> {
      if (beacon.memory.remove(Constants.BUFFER_SIZE) < Constants.BUFFER_SIZE) {
        listenerManager.emitEvent(new StopSyncEvent(this));
        beacon.listenerManager.emitEvent(new StopSyncEvent(beacon));
      }
    });
  }

  /**
   * {@linkplain StopSyncEvent} listener.
   * 
   * @param event
   */
  public void onStopEvent(final StopSyncEvent event) {
    this.movement = this.oldMovement;
  }

  protected final Port<Satellite> port;
  protected Movement oldMovement;
}
