package edu.ubo.satellitebeacons.main.commands.constructors;

import java.lang.reflect.Constructor;
import java.util.Map;
import edu.ubo.satellitebeacons.main.commands.values.ObjectValue;
import edu.ubo.satellitebeacons.main.commands.values.Value;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.movable.movement.HorizontalMovement;
import edu.ubo.satellitebeacons.main.movable.movement.Movement;
import edu.ubo.satellitebeacons.main.movable.movement.SimpleHorizontalMovement;
import edu.ubo.satellitebeacons.main.space.Position;
import edu.ubo.satellitebeacons.main.utils.Constants;
import edu.ubo.satellitebeacons.main.utils.Utils;

public class Constructors {
  protected static final int MIN_X = 0;
  protected static final int MAX_X = 600;
  protected static final int MIN_Y_SATELLITE = 10;
  protected static final int MAX_Y_SATELLITE = Constants.SEA_LEVEL - 50;
  protected static final int MIN_Y_BEACON = Constants.SEA_LEVEL + 25;
  protected static final int MAX_Y_BEACON = 700;

  public Constructors() {
    this.port = new Port<>();
  }

  public Value<Object> newSatellite(final Map<String, Object> args) {
    final var s = new Satellite(new Position(Utils.parseIntOrRandom(args.get("x"), MIN_X, MAX_X),
        Utils.parseIntOrRandom(args.get("y"), MIN_Y_SATELLITE, MAX_Y_SATELLITE)), port);
    s.setMovement(this.createMovement(SimpleHorizontalMovement.class, args));
    return new ObjectValue(s);
  }

  public Value<Object> newBeacon(final Map<String, Object> args) {
    final var b = new Beacon(new Position(Utils.parseIntOrRandom(args.get("x"), MIN_X, MAX_X),
        Utils.parseIntOrRandom(args.get("y"), MIN_Y_BEACON, MAX_Y_BEACON)), port);
    b.setMovement(this.createMovement(HorizontalMovement.class, args));
    return new ObjectValue(b);
  }

  public Value<Object> newHorizontalMovement(final Map<String, Object> args) {
    return new ObjectValue(this.createMovement(HorizontalMovement.class, args));
  }

  public Value<Object> newVerticalMovement(final Map<String, Object> args) {
    return new ObjectValue(this.createMovement(SimpleHorizontalMovement.class, args));
  }

  protected <T extends Movement> T createMovement(final Class<T> cl,
      final Map<String, Object> args) {
    Constructor<T> contructor;
    try {
      contructor = cl.getConstructor(int.class, int.class, int.class);
      return contructor.newInstance(Utils.parseIntOrDefault(args.get("min"), 0),
          Utils.parseIntOrDefault(args.get("max"), 400), 10);
    } catch (Exception e) {
      // Unreachable code
      return null;
    }
  }

  protected final Port<Satellite> port;
}
