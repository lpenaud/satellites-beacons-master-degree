package edu.ubo.satellitebeacons.main.movable;

import java.util.EventObject;
import edu.ubo.satellitebeacons.main.event.EventManager;
import edu.ubo.satellitebeacons.main.event.FullCapacityEvent;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
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
    public void emitFullCapacity() {
      this.eventManager.emitEvent(new FullCapacityEvent(this));
    }
    
    public void add(final int i) {
      if (this.used < this.capacity) {
        this.used += i;        
      } else {
        this.emitFullCapacity();        
      }
    }

    protected final EventManager eventManager;
    protected final int capacity;
    protected int used;
  }

  public Beacon(final Position position) {
    this.position = position;
    this.memory = new Memory(50);
    this.memory.addEventListener(FullCapacityEvent.class, this::onFullCapacity);
  }
  
  @Override
  public void move() {
    super.move();
    this.memory.add(1);
  }
  
  @Override
  public void onFullCapacity(final FullCapacityEvent event) {
    // TODO: Keep the previous movement
    final var movement = new UpMovement(Constants.SEA_LEVEL, 5);
    movement.addEventListener(PositionChangedEvent.class, this::reachSeaLevel);
    this.movement = movement;
  }
  
  public void reachSeaLevel(final PositionChangedEvent event) {
    // TODO: On cherche le satellite
    this.movement = new MovementLess();
  }
  
  protected final Memory memory;
}
