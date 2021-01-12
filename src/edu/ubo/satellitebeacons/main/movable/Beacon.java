package edu.ubo.satellitebeacons.main.movable;

import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.FullCapacityEvent;
import edu.ubo.satellitebeacons.main.event.MessageEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.event.listener.Listener;
import edu.ubo.satellitebeacons.main.movable.movement.DirectionalMovement;
import edu.ubo.satellitebeacons.main.simulation.utils.Constants;
import edu.ubo.satellitebeacons.main.space.Position;

/**
 * Simulate the beacon behavior.
 */
public class Beacon extends Movable {
  
  /**
   * Memory of the beacon.
   */
  public class Memory {
    
    /**
     * Create a new memory with the given capacity.
     * @param capacity Max capacity of the memory.
     */
    public Memory(final int capacity) {
      this.capacity = capacity;
    }
    
    /**
     * Add things to the memory.
     * @param i Size of the things to be added.
     */
    public void add(final int i) {
      if (this.isFull()) {
        fullCapacity();
      } else {
        this.used += i;
      }
    }
    
    /**
     * Remove things to the memory.
     * @param i Size of the things to remove.
     * @return Size of removed resources.
     */
    public int remove(final int i) {
      if (this.used - i < 0) {
        final int res = this.used;
        this.used = 0;
        return res;
      }
      this.used -= i;
      return i;
    }
    
    /**
     * Clean the memory by remove everything on it.
     */
    public void clean() {
      this.used = 0;
    }
    
    /**
     * Test if the memory is full.
     * @return true if the memory is full otherwise false.
     */
    public boolean isFull() {
      return this.used >= this.capacity;
    }
    
    /**
     * Test if the memory is empty.
     * @return true if the memory is empty otherwise false.
     */
    public boolean isEmpty() {
      return this.used == 0;
    }

    protected final int capacity;
    protected int used;
  }

  /**
   * Create a new beacon with the given position and a communicate port.
   * @param position Departure position of the beacon.
   * @param port Communication port with all the satellites.
   */
  public Beacon(final Position position, final Port<Satellite> port) {
    this.position = position;
    this.memory = new Memory(50);
    this.port = port;
    this.readSensors = true;
    this.reachSeaLevelListener = this::onReachSeaLevel;
    this.satelliteMessageListener = this::onSatelliteMessage;
    this.reachOldPositionListener = this::onReachOldPosition;
  }
  
  @Override
  public void move() {
    super.move();
    if (this.readSensors) {
      this.memory.add(1);
    }
  }
  
  /**
   * Method if the memory is full.
   * Stop to read sensors.
   * Emit a {@linkplain FullCapacityEvent}.
   */
  public void fullCapacity() {
    this.readSensors = false;
    this.listenerManager.emitEvent(new FullCapacityEvent(this));
  }
  
  /**
   * Reach sea level listener.
   * @param event
   */
  public void onReachSeaLevel(final DestinationReachEvent event) {
    ((DirectionalMovement) event.getSource()).removeEventListener(DestinationReachEvent.class, this.reachOldPositionListener);
    this.port.addEventListener(MessageEvent.class, this.satelliteMessageListener);
  }
  
  /**
   * Message event from a satellite with the communication port.
   * @param event
   */
  protected void onSatelliteMessage(final MessageEvent<Satellite> event) {
    final var diff = this.position.getX() - event.getContent().getPosition().getX();
    if (diff >= Constants.MIN_RADIUS && diff <= Constants.MAX_RADIUS) {
      this.port.removeEventListener(MessageEvent.class, this.satelliteMessageListener);
      event.getContent().receive(this);
    }
  }
  
  /**
   * Reach old position listener.
   * @param event
   */
  protected void onReachOldPosition(final DestinationReachEvent event) {
    this.readSensors = true;
    ((DirectionalMovement) event.getSource()).removeEventListener(DestinationReachEvent.class, this.reachOldPositionListener);
  }
  
  /**
   * Return the old position listener.
   * @return Reach old position listener.
   */
  public Listener<DestinationReachEvent> getReachOldPositionListener() {
    return reachOldPositionListener;
  }
  
  /**
   * Return the reach sea level listener.
   * @return Reach sea level listener.
   */
  public Listener<DestinationReachEvent> getReachSeaLevelListener() {
    return reachSeaLevelListener;
  }
  
  protected final Memory memory;
  protected final Port<Satellite> port;
  protected boolean readSensors;
  
  // Listeners
  @SuppressWarnings("rawtypes")
  protected final Listener<MessageEvent> satelliteMessageListener;
  protected final Listener<DestinationReachEvent> reachSeaLevelListener;
  protected final Listener<DestinationReachEvent> reachOldPositionListener;
}
