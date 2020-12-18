package edu.ubo.satellitebeacons.main.movable;

import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.event.listener.PositionChangedListener;
import edu.ubo.satellitebeacons.main.movable.Beacon.Memory;
import edu.ubo.satellitebeacons.main.space.Position;

public class Satellite extends Movable implements PositionChangedListener {

  public Satellite(final Position position, final Port<Satellite> port) {
    this.position = position;
    this.port = port;
    this.listenerManager.addEventListener(PositionChangedEvent.class, this::onPositionChangedEvent);
  }

  @Override
  public void onPositionChangedEvent(final PositionChangedEvent event) {
    this.port.send(this);
  }
  
  public void receive(final Memory memory) {
    System.out.println(new StringBuilder("Receive memory: ").append(memory));
    memory.clean();
  }
  
  protected final Port<Satellite> port;

}
