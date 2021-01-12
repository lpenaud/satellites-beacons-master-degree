package edu.ubo.satellitebeacons.main.movable;

import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.StartSyncEvent;
import edu.ubo.satellitebeacons.main.event.StopSyncEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.event.listener.PositionChangedListener;
import edu.ubo.satellitebeacons.main.movable.movement.Movement;
import edu.ubo.satellitebeacons.main.movable.movement.PrgmMovement;
import edu.ubo.satellitebeacons.main.space.Position;

public class Satellite extends Movable implements PositionChangedListener {
  public static final int BUFFER_SIZE = 10;
  
  public Satellite(final Position position, final Port<Satellite> port) {
    this.position = position;
    this.port = port;
    this.listenerManager.addEventListener(PositionChangedEvent.class, this::onPositionChangedEvent);
    this.listenerManager.addEventListener(StopSyncEvent.class, this::onStopEvent);
  }

  @Override
  public void onPositionChangedEvent(final PositionChangedEvent event) {
    this.port.send(this);
  }
  
  public void receive(final Beacon beacon) {
    this.listenerManager.emitEvent(new StartSyncEvent(this));
    beacon.listenerManager.emitEvent(new StartSyncEvent(beacon));
    this.oldMovement = this.movement;
    this.movement = new PrgmMovement(() -> {
      if (beacon.memory.remove(BUFFER_SIZE) < BUFFER_SIZE) {
        listenerManager.emitEvent(new StopSyncEvent(this));
        beacon.listenerManager.emitEvent(new StopSyncEvent(beacon));
      }
    });
  }
  
  public void onStopEvent(final StopSyncEvent event) {
    this.movement = this.oldMovement;
  }
  
  protected final Port<Satellite> port;
  protected Movement oldMovement;
}
