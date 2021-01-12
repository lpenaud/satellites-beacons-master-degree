package edu.ubo.satellitebeacons.main.movable;

import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.FullCapacityEvent;
import edu.ubo.satellitebeacons.main.event.MessageEvent;
import edu.ubo.satellitebeacons.main.event.StopSyncEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.movable.movement.DirectionalMovement;
import edu.ubo.satellitebeacons.main.simulation.utils.Constants;
import edu.ubo.satellitebeacons.main.space.Position;

public class Beacon extends Movable {
  public static final int SPEED = 5;
  
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
    this.reachSeaLevelListener = this::onReachSeaLevel;
    this.satelliteMessageListener = this::onSatelliteMessage;
    this.reachOldPositionListener = this::onReachOldPosition;
  }
  
  @Override
  public void move() {
    super.move();
    if (this.takePhoto) {
      this.memory.add(1);
    }
  }
  
  public void fullCapacity() {
    this.takePhoto = false;
    this.listenerManager.emitEvent(new FullCapacityEvent(this));
  }
  
  public void onReachSeaLevel(final DestinationReachEvent event) {
    ((DirectionalMovement) event.getSource()).removeEventListener(DestinationReachEvent.class, this.reachOldPositionListener);
    this.port.addEventListener(MessageEvent.class, this.satelliteMessageListener);
  }
  
  protected void onSatelliteMessage(final MessageEvent<Satellite> event) {
    final var diff = this.position.getX() - event.getContent().getPosition().getX();
    if (diff >= Constants.MIN_RADIUS && diff <= Constants.MAX_RADIUS) {
      this.port.removeEventListener(MessageEvent.class, this.satelliteMessageListener);
      event.getContent().receive(this);
    }
  }
  
  protected void onReachOldPosition(final DestinationReachEvent event) {
    this.takePhoto = true;
    ((DirectionalMovement) event.getSource()).removeEventListener(DestinationReachEvent.class, this.reachOldPositionListener);
  }
  
  public Listener<DestinationReachEvent> getReachOldPositionListener() {
    return reachOldPositionListener;
  }
  
  public Listener<DestinationReachEvent> getReachSeaLevelListener() {
    return reachSeaLevelListener;
  }
  
  protected final Memory memory;
  protected final Port<Satellite> port;
  protected boolean takePhoto;
  
  // Listeners
  @SuppressWarnings("rawtypes")
  protected final Listener<MessageEvent> satelliteMessageListener;
  protected final Listener<DestinationReachEvent> reachSeaLevelListener;
  protected final Listener<DestinationReachEvent> reachOldPositionListener;
}
