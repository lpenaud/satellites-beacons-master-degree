package edu.ubo.satellitebeacons.main.simulation;

import java.awt.Dimension;
import edu.ubo.graphicLayer.GSpace;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Position;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.movable.movement.HorizontalMovement;
import edu.ubo.satellitebeacons.main.movable.movement.VerticalMovement;
import edu.ubo.satellitebeacons.main.simulation.components.GBeacon;
import edu.ubo.satellitebeacons.main.simulation.components.GSatellite;
import edu.ubo.satellitebeacons.main.simulation.components.GSea;

public class Simulation implements Runnable {
  
  public static void main(String[] args) {
    final var thread = new Thread(new Simulation());
    thread.start();
  }
  
  public Simulation() {
    satellite = new Satellite(new Position(0, 0));
    beacon = new Beacon(new Position(400, 300));
  }
  
  public GSpace setup() {
    final var window = new GSpace("Simulation", new Dimension(800, 600));    
    final var gSea = new GSea();
    final var gBeacon = new GBeacon();
    final var gSatellite = new GSatellite();
    beacon.addEventListener(PositionChangedEvent.class, gBeacon);
    beacon.setMovement(new VerticalMovement(300 - gBeacon.getHeight() / 2, 600 - gBeacon.getHeight()));
    
    satellite.addEventListener(PositionChangedEvent.class, gSatellite);
    satellite.setMovement(new HorizontalMovement(-100, 900));
    
    window.addElement(gSea);
    window.addElement(gBeacon);
    window.addElement(gSatellite);
    return window;
  }

  @Override
  public void run() {
    final var window = this.setup();
    window.open();
    while (true) {
      satellite.move();
      beacon.move();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  protected final Satellite satellite;
  protected final Beacon beacon;
  
}
