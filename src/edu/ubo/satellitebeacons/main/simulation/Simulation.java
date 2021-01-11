package edu.ubo.satellitebeacons.main.simulation;

import java.awt.Dimension;
import edu.ubo.graphicLayer.GSpace;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.event.SyncEvent;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.movable.movement.DownMovement;
import edu.ubo.satellitebeacons.main.movable.movement.HorizontalMouvement;
import edu.ubo.satellitebeacons.main.movable.movement.LeftMovement;
import edu.ubo.satellitebeacons.main.simulation.components.GBeacon;
import edu.ubo.satellitebeacons.main.simulation.components.GSatellite;
import edu.ubo.satellitebeacons.main.simulation.components.GSea;
import edu.ubo.satellitebeacons.main.simulation.utils.Constants;
import edu.ubo.satellitebeacons.main.space.Position;

public class Simulation implements Runnable {
  
  public static void main(String[] args) {
    final var thread = new Thread(new Simulation());
    thread.start();
  }
  
  public Simulation() {
    final Port<Satellite> port = new Port<>();
    satellite = new Satellite(new Position(0, 0), port);
    beacon = new Beacon(new Position(400, Constants.SEA_LEVEL + 20), port);
    beacon.addEventListener(SyncEvent.class, this::onSync);
  }
  
  public GSpace setup() {
    final var window = new GSpace("Simulation", new Dimension(800, 600));    
    final var gSea = new GSea();
    final var gBeacon = new GBeacon();
    final var gSatellite = new GSatellite();

    beacon.addEventListener(PositionChangedEvent.class, gBeacon::onPositionChangedEvent);
    beacon.setMovement(new LeftMovement(0, 4));    
    satellite.addEventListener(PositionChangedEvent.class, gSatellite::onPositionChangedEvent);
    satellite.setMovement(new HorizontalMouvement(-100, 900, 10));

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
      System.out.println(beacon.getPosition());
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void onSync(final SyncEvent event) {
    System.out.println(event);
    this.beacon.setMovement(new DownMovement(300, 5));
  }
  
  protected final Satellite satellite;
  protected final Beacon beacon;
  
}
