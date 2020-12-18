package edu.ubo.satellitebeacons.main.movable;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.EventManager;
import edu.ubo.satellitebeacons.main.event.FullCapacityEvent;
import edu.ubo.satellitebeacons.main.event.MessageEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.event.emitter.FullCapacityEmitter;
import edu.ubo.satellitebeacons.main.event.listener.FullCapacityListener;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.movable.movement.MovementLess;
import edu.ubo.satellitebeacons.main.movable.movement.UpMovement;
import edu.ubo.satellitebeacons.main.simulation.utils.Constants;
import edu.ubo.satellitebeacons.main.space.Position;

public class Beacon extends Movable implements FullCapacityListener {
  
  public class Memory implements FullCapacityEmitter {
    public Memory(final int capacity) {
      this.capacity = capacity;
      this.eventManager = new EventManager();
    }

    @Override
    public <E extends EventObject> void addEventListener(final Class<E> event, final Listener<E> l) {
      this.eventManager.addEventListener(event, l);
    }
    
    @Override
    public <E extends EventObject> void removeEventListener(final Class<E> event, final Listener<E> l) {
      this.eventManager.removeEventListener(event, l);
    }

    @Override
    public void emitFullCapacity() {
      this.eventManager.emitEvent(new FullCapacityEvent(this));
    }
    
    public void add(final int i) {
      if (this.isFull()) {
        this.emitFullCapacity();        
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

    protected final EventManager eventManager;
    protected final int capacity;
    protected int used;
  }

  public Beacon(final Position position, final Port<Satellite> port) {
    this.position = position;
    this.memory = new Memory(50);
    this.memory.addEventListener(FullCapacityEvent.class, this::onFullCapacity);
    this.port = port;
  }
  
  @Override
  public void move() {
    super.move();
    if (!(this.movement instanceof UpMovement) && !(this.movement instanceof MovementLess)) {
      this.memory.add(1);
    }
  }
  
  @Override
  public void onFullCapacity(final FullCapacityEvent event) {
    final var movement = new UpMovement(Constants.SEA_LEVEL, 5);
    movement.addEventListener(DestinationReachEvent.class, this::reachSeaLevel);
    this.movement = movement;
  }
  
  public void reachSeaLevel(final DestinationReachEvent event) {
    ((UpMovement) event.getSource()).removeEventListener(event.getClass(), this::reachSeaLevel);
    this.movement = new MovementLess();
    this.port.addEventListener(MessageEvent.class, this::onMessage);
  }
  
  public void onMessage(final MessageEvent<Satellite> event) {
    final var diff = this.position.getX() - event.getContent().getPosition().getX();
    if (diff >= Constants.MIN_RADIUS && diff <= Constants.MAX_RADIUS) {
      event.getContent().receive(this.memory);
      this.port.removeEventListener(event.getClass(), this::onMessage);
    }
  }
  
  protected final Memory memory;
  protected final Port<Satellite> port;
}
