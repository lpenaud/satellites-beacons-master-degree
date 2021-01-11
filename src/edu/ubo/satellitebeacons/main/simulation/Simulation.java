package edu.ubo.satellitebeacons.main.simulation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import edu.ubo.graphicLayer.GSpace;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.StartSyncEvent;
import edu.ubo.satellitebeacons.main.event.StopSyncEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Movable;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.movable.movement.HorizontalMovement;
import edu.ubo.satellitebeacons.main.movable.movement.Movement;
import edu.ubo.satellitebeacons.main.simulation.components.GBeacon;
import edu.ubo.satellitebeacons.main.simulation.components.GMovable;
import edu.ubo.satellitebeacons.main.simulation.components.GSatellite;
import edu.ubo.satellitebeacons.main.simulation.components.GSea;
import edu.ubo.satellitebeacons.main.simulation.utils.Constants;
import edu.ubo.satellitebeacons.main.space.Position;

public class Simulation implements Runnable {
  
  public static void main(String[] args) throws InterruptedException {
    final var simulation = new Simulation();
    simulation.setup();
    simulation.open();
    simulation.run();
//    thread.start();
//    thread.join();
  }
  
  public Simulation() {
    this.portSatellite = new Port<>();
    this.window = new GSpace("Simulation", new Dimension(800, 600));
    this.beacons = new ArrayList<>();
    this.satellites = new ArrayList<>();
  }
  
  public void addBeacon(final Position p, final Movement movement) {
    final var beacon = new Beacon(p, portSatellite);
    beacon.setMovement(movement);
    this.addMovable(beacon, new GBeacon());
    this.beacons.add(beacon);
  }
  
  public void addSatellite(final Position p) {
    final var satellite = new Satellite(p, portSatellite);
    satellite.setMovement(new HorizontalMovement(-100, 900, 10));
    this.addMovable(satellite, new GSatellite());
    this.satellites.add(satellite);
  }
  
  public void addMovable(final Movable movable, final GMovable gMovable) {
    movable.addEventListener(PositionChangedEvent.class, gMovable::onPositionChangedEvent);
    movable.addEventListener(StartSyncEvent.class, gMovable::onStartSync);
    movable.addEventListener(StopSyncEvent.class, gMovable::onStopSync);
    this.window.addElement(gMovable);
  }
  
  public GSpace setup() {
    final var gSea = new GSea();
    window.addElement(gSea);
    this.addSatellite(new Position(10, 50));
    this.addSatellite(new Position(100, 10));
    this.addSatellite(new Position(400, 90));
    this.addSatellite(new Position(500, 140));
    this.addSatellite(new Position(600, 10));
    this.addBeacon(new Position(400, 200 + Constants.SEA_LEVEL), new HorizontalMovement(50, 750, 10));
    this.addBeacon(new Position(0, 160 + Constants.SEA_LEVEL), new HorizontalMovement(0, 800, 10));
    this.addBeacon(new Position(300, 100 + Constants.SEA_LEVEL), new HorizontalMovement(200, 600, 10));
    return window;
  }
  
  public void open() {
    javax.swing.SwingUtilities.invokeLater(window::open);
  }

  @Override
  public void run() {
    final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    final var beaconsCalls = beacons.stream()
        .map(b -> Executors.callable(b::move))
        .collect(Collectors.toList());
    final var satelliteCalls = satellites.stream()
        .map(s -> Executors.callable(s::move))
        .collect(Collectors.toList());
    try {
      while (true) {
        executor.invokeAll(beaconsCalls);
        executor.invokeAll(satelliteCalls);
        Thread.sleep(100);
      }
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  protected final Port<Satellite> portSatellite;
  protected final GSpace window;
  protected final List<Beacon> beacons;
  protected final List<Satellite> satellites;
  
}
