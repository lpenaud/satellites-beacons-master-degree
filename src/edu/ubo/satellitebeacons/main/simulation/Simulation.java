package edu.ubo.satellitebeacons.main.simulation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import edu.ubo.graphicLayer.GSpace;
import edu.ubo.satellitebeacons.main.event.FullCapacityEvent;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.StartSyncEvent;
import edu.ubo.satellitebeacons.main.event.StopSyncEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Movable;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.movable.movement.HorizontalMovement;
import edu.ubo.satellitebeacons.main.movable.movement.SimpleHorizontalMovement;
import edu.ubo.satellitebeacons.main.movable.movement.StackMovement;
import edu.ubo.satellitebeacons.main.simulation.components.GBeacon;
import edu.ubo.satellitebeacons.main.simulation.components.GMovable;
import edu.ubo.satellitebeacons.main.simulation.components.GSatellite;
import edu.ubo.satellitebeacons.main.simulation.components.GSea;
import edu.ubo.satellitebeacons.main.utils.Constants;
import edu.ubo.satellitebeacons.main.utils.GraphicUtils;
import edu.ubo.satellitebeacons.main.utils.Utils;
import edu.ubo.satellitebeacons.main.space.Position;

public class Simulation implements Runnable {

  public static void main(String[] args) {
    final var simulation = new Simulation();
    simulation.open();
    simulation.setup();
    simulation.run();
  }

  public Simulation() {
    this.portSatellite = new Port<>();
    this.window = new GSpace("Simulation", new Dimension(800, 600));
    this.movables = new ArrayList<>();
  }

  public void addBeacon(final Beacon b) {
    this.addMovable(b, new GBeacon());
    try {
      final StackMovement movement = (StackMovement) b.getMovement();
      b.addEventListener(FullCapacityEvent.class, movement::onFullCapacity);
      b.addEventListener(StopSyncEvent.class, movement::onStopSync);
    } catch (Exception e) {
      System.err.println(new StringBuilder("Warning the movement of ").append(b)
          .append(" don't listen FullCapacityEvent and StopSyncEvent"));
    }
  }

  public void addBeacon(final Position p, final StackMovement movement) {
    final var beacon = new Beacon(p, portSatellite);
    beacon.setMovement(movement);
    beacon.addEventListener(FullCapacityEvent.class, movement::onFullCapacity);
    beacon.addEventListener(StopSyncEvent.class, movement::onStopSync);
    this.addBeacon(beacon);
  }

  public void addSatellite(final Satellite s) {
    this.addMovable(s, new GSatellite());
  }

  public void addSatellite(final Position p) {
    final var s = new Satellite(p, portSatellite);
    s.setMovement(new SimpleHorizontalMovement(-100, 900, Utils.getSatelliteSpeed(p)));
    this.addSatellite(s);
  }

  public void addMovable(final Movable movable, final GMovable gMovable) {
    movable.addEventListener(PositionChangedEvent.class, gMovable::onPositionChangedEvent);
    movable.addEventListener(StartSyncEvent.class, gMovable::onStartSync);
    movable.addEventListener(StopSyncEvent.class, gMovable::onStopSync);
    this.window.addElement(gMovable);
    this.movables.add(movable);
    gMovable.setPosition(GraphicUtils.positionToPoint(movable.getPosition()));
    gMovable.repaint();
  }

  public GSpace setup() {
    this.addSatellite(new Position(10, 50));
    this.addSatellite(new Position(100, 10));
    this.addSatellite(new Position(400, 90));
    this.addSatellite(new Position(500, 140));
    this.addSatellite(new Position(600, 10));
    this.addBeacon(new Position(400, 200 + Constants.SEA_LEVEL),
        new HorizontalMovement(50, 750, 10));
    this.addBeacon(new Position(0, 160 + Constants.SEA_LEVEL), new HorizontalMovement(0, 800, 10));
    this.addBeacon(new Position(300, 100 + Constants.SEA_LEVEL),
        new HorizontalMovement(200, 600, 10));
    return window;
  }

  public void open() {
    window.addElement(new GSea());
    window.open();
  }

  @Override
  public void run() {
    final ExecutorService executor =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    final Collection<Callable<Object>> callables = movables.stream()
        .map(m -> Executors.callable(m::move)).collect(Collectors.toUnmodifiableList());
    try {
      while (true) {
        executor.invokeAll(callables);
        Thread.sleep(100);
      }
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  protected final Port<Satellite> portSatellite;
  protected final GSpace window;
  protected final List<Movable> movables;

}
