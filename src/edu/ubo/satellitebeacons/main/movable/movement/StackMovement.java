package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.event.DestinationReachEvent;
import edu.ubo.satellitebeacons.main.event.FullCapacityEvent;
import edu.ubo.satellitebeacons.main.event.StopSyncEvent;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Movable;
import edu.ubo.satellitebeacons.main.utils.Constants;

public class StackMovement implements Movement {
  public static class Node {
    public Node(final Movement movement) {
      this.movement = movement;
    }

    public Node(final Movement movement, final Node next) {
      this.movement = movement;
      this.next = next;
    }

    protected final Movement movement;
    protected Node next;
  }

  public StackMovement(final DirectionalMovement movement) {
    this.root = new Node(movement);
    movement.addEventListener(DestinationReachEvent.class, this::onDestinationReachEvent);
    this.goSea = new UpMovement(Constants.SEA_LEVEL, Constants.BEACON_SPEED);
    this.goSea.addEventListener(DestinationReachEvent.class, this::onDestinationReachEvent);
  }

  public void setMovements(final DirectionalMovement movement,
      final DirectionalMovement... movements) {
    var node = this.root.next = new Node(movement);
    movement.addEventListener(DestinationReachEvent.class, this::onDestinationReachEvent);
    for (int i = 0; i < movements.length; i++) {
      movements[i].addEventListener(DestinationReachEvent.class, this::onDestinationReachEvent);
      node.next = new Node(movements[i]);
      node = node.next;
    }
    node.next = this.root;
  }

  @Override
  public void move(final Movable movable) {
    this.root.movement.move(movable);
  }

  public void onFullCapacity(final FullCapacityEvent event) {
    final var beacon = ((Beacon) event.getSource());
    // Create a factory method to create and add listener to a new directional movement?
    final var downMovement = new DownMovement(beacon.getPosition().getY(), Constants.BEACON_SPEED);
    downMovement.addEventListener(DestinationReachEvent.class, this::onDestinationReachEvent);
    this.goSea.addEventListener(DestinationReachEvent.class, beacon.getReachSeaLevelListener());
    this.root = new Node(this.goSea, new Node(MovementLess.SINGLETON, new Node(downMovement, this.root)));
  }

  public void onDestinationReachEvent(final DestinationReachEvent event) {
    this.root = this.root.next;
  }

  public void onStopSync(final StopSyncEvent event) {
    this.root = this.root.next;
    ((DirectionalMovement) this.root.movement).addEventListener(DestinationReachEvent.class,
        ((Beacon) event.getSource()).getReachOldPositionListener());
  }

  protected Node root;
  protected final DirectionalMovement goSea;
}
