package edu.ubo.satellitebeacons.main.movable;

import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.MessageEvent;
import edu.ubo.satellitebeacons.main.event.StartSyncEvent;
import edu.ubo.satellitebeacons.main.event.StopSyncEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.movable.movement.DownMovement;
import edu.ubo.satellitebeacons.main.movable.movement.MovementLess;
import edu.ubo.satellitebeacons.main.movable.movement.UpMovement;
import edu.ubo.satellitebeacons.main.simulation.utils.Constants;
import edu.ubo.satellitebeacons.main.space.Position;

public class Beacon extends Movable {
  
  public class Memory {
    public Memory(final int capacity) {
      this.capacity = capacity;
    }
    
    public void add(final int i) {
      if (this.isFull()) {
        fullCapacity();
      } else {
        this.used += i;
      }
    }
    
    public int remove(final int i) {
      if (this.used - i < 0) {
        final int res = this.used;
        this.used = 0;
        return res;
      }
      this.used -= i;
      return i;
    }
    
    public void clean() {
      this.used = 0;
    }
    
    public boolean isFull() {
      return this.used >= this.capacity;
    }
    
    public boolean isEmpty() {
      return this.used == 0;
    }

    protected final int capacity;
    protected int used;
  }

  public Beacon(final Position position, final Port<Satellite> port) {
    this.position = position;
    this.memory = new Memory(50);
    this.port = port;
    this.takePhoto = true;
    this.reachSeaLevelListener = this::reachSeaLevel;
    this.satelliteMessageListener = this::onSatelliteMessage;
    this.stopSyncListener = this::onStopSync;
  }
  
  @Override
  public void move() {
    super.move();
    if (this.takePhoto) {
      this.memory.add(1);
    }
  }
  
  public void fullCapacity() {
    final var movement = new UpMovement(Constants.SEA_LEVEL, 5);
    movement.addEventListener(DestinationReachEvent.class, this.reachSeaLevelListener);
    this.movement = movement;
    this.takePhoto = false;
  }
  
  public void reachSeaLevel(final DestinationReachEvent event) {
    ((UpMovement) event.getSource()).removeEventListener(DestinationReachEvent.class, this.reachSeaLevelListener);
    this.movement = new MovementLess();
    this.port.addEventListener(MessageEvent.class, this.satelliteMessageListener);
  }
  
  public void onSatelliteMessage(final MessageEvent<Satellite> event) {
    final var diff = this.position.getX() - event.getContent().getPosition().getX();
    if (diff >= Constants.MIN_RADIUS && diff <= Constants.MAX_RADIUS) {
      event.getContent().listenerManager.addEventListener(StopSyncEvent.class, this.stopSyncListener);
      this.port.removeEventListener(MessageEvent.class, this.satelliteMessageListener);
      event.getContent().receive(this);
    }
  }
  
  public void onStopSync(final StopSyncEvent event) {
    this.setMovement(new DownMovement(700, 5));
    this.takePhoto = true;
    ((Satellite) event.getSource()).removeEventListener(StopSyncEvent.class, this.stopSyncListener);
  }
  
  protected final Memory memory;
  protected final Port<Satellite> port;
  protected boolean takePhoto;
  
  // Listeners
  protected final Listener<DestinationReachEvent> reachSeaLevelListener;
  @SuppressWarnings("rawtypes")
  protected final Listener<MessageEvent> satelliteMessageListener;
  protected final Listener<StopSyncEvent> stopSyncListener;
}
