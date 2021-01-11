package edu.ubo.satellitebeacons.main.movable;

import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.MessageEvent;
import edu.ubo.satellitebeacons.main.event.SyncEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
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
    
    public void clean() {
      this.used = 0;
    }
    
    public boolean isFull() {
      return this.used >= this.capacity;
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
    this.satelliteMessageListener = this::onMessage;
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
  
  public void onMessage(final MessageEvent<Satellite> event) {
    final var diff = this.position.getX() - event.getContent().getPosition().getX();
    if (diff >= Constants.MIN_RADIUS && diff <= Constants.MAX_RADIUS) {
      event.getContent().receive(this.memory);
      this.port.removeEventListener(MessageEvent.class, this.satelliteMessageListener);
      this.listenerManager.emitEvent(new SyncEvent(this));
      this.takePhoto = true;
    }
  }
  
  protected final Memory memory;
  protected final Port<Satellite> port;
  protected boolean takePhoto;
  protected final Listener<DestinationReachEvent> reachSeaLevelListener;
  @SuppressWarnings("rawtypes")
  protected final Listener<MessageEvent> satelliteMessageListener;
}
