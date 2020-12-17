package edu.ubo.satellitebeacons.main.simulation;

import java.awt.Dimension;
import edu.ubo.graphicLayer.GSpace;
import edu.ubo.satellitebeacons.main.event.PositionChangedEvent;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.movable.movement.HorizontalMouvement;
import edu.ubo.satellitebeacons.main.movable.movement.LeftMovement;
import edu.ubo.satellitebeacons.main.movable.movement.StackMovement;
import edu.ubo.satellitebeacons.main.movable.movement.VerticalMovement;
import edu.ubo.satellitebeacons.main.simulation.components.GBeacon;
import edu.ubo.satellitebeacons.main.simulation.components.GSatellite;
import edu.ubo.satellitebeacons.main.simulation.components.GSea;
import edu.ubo.satellitebeacons.main.space.Position;
import edu.ubo.satellitebeacons.main.space.SpaceFactory;

public class Simulation implements Runnable {
  
  public static void main(String[] args) {
    final var thread = new Thread(new Simulation());
    thread.start();
  }
  
  public Simulation() {
    satellite = new Satellite(SpaceFactory.SINGLETON.getPosition(0, 0));
    beacon = new Beacon(SpaceFactory.SINGLETON.getPosition(400, 300));
  }
  
  public GSpace setup() {
    final var window = new GSpace("Simulation", new Dimension(800, 600));    
    final var gSea = new GSea();
    final var gBeacon = new GBeacon();
    final var gSatellite = new GSatellite();
 
    beacon.addEventListener(PositionChangedEvent.class, gBeacon::onPositionChangedEvent);
    beacon.setMovement(new HorizontalMouvement(10, 690, 10));    
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
  
  protected final Satellite satellite;
  protected final Beacon beacon;
  
}
