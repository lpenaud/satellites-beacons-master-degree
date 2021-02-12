package edu.ubo.satellitebeacons.main.commands.constructors;

import java.util.Map;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.movable.movement.HorizontalMovement;
import edu.ubo.satellitebeacons.main.movable.movement.SimpleHorizontalMovement;
import edu.ubo.satellitebeacons.main.space.Position;
import edu.ubo.satellitebeacons.main.utils.Constants;
import edu.ubo.satellitebeacons.main.utils.Utils;

public class Constructors {
  protected static final Integer[] X_AXIS = { 10, 100, 400, 500, 600, 0, 300 };
  protected static final Integer[] Y_AXIS_SATELLITE = { 50, 10, 90, 140 };
  protected static final Integer[] Y_AXIS_BEACON;
  
  static {
    Y_AXIS_BEACON = new Integer[Y_AXIS_SATELLITE.length];
    for (int i = 0; i < Y_AXIS_BEACON.length; i++) {
      Y_AXIS_BEACON[i] = Y_AXIS_SATELLITE[i] + Constants.SEA_LEVEL; 
    }
  }

  public Constructors() {
    this.port = new Port<>();
  }

  public Satellite newSatellite(final Map<String, Object> args) {
    final var s = new Satellite(new Position(Utils.parseIntOrRandom(args.get("x"), X_AXIS),
        Utils.parseIntOrRandom(args.get("y"), Y_AXIS_SATELLITE)), port);
    s.setMovement(this.newVerticalMovement(args));
    return s;
  }

  public Beacon newBeacon(final Map<String, Object> args) {
    final var b = new Beacon(new Position(Utils.parseIntOrRandom(args.get("x"), X_AXIS),
        Utils.parseIntOrRandom(args.get("y"), Y_AXIS_BEACON)), port);
    b.setMovement(this.newHorizontalMovement(args));
    return b;
  }

  public HorizontalMovement newHorizontalMovement(final Map<String, Object> args) {
    return new HorizontalMovement(Utils.parseIntOrDefault(args.get("min"), 0),
        Utils.parseIntOrDefault(args.get("max"), 400), 10);
  }

  public SimpleHorizontalMovement newVerticalMovement(final Map<String, Object> args) {
    return new SimpleHorizontalMovement(Utils.parseIntOrDefault(args.get("min"), 0),
        Utils.parseIntOrDefault(args.get("max"), 400), 10);
  }

  protected final Port<Satellite> port;
}
