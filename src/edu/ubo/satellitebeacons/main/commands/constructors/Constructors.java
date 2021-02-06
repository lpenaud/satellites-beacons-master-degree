package edu.ubo.satellitebeacons.main.commands.constructors;

import java.util.Map;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.movable.Beacon;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.space.Position;
import edu.ubo.satellitebeacons.main.utils.Utils;

public class Constructors {

  public Constructors() {
    this.port = new Port<>();
  }

  public Satellite newSatellite(final Map<String, String> args) {
    return new Satellite(new Position(Utils.parseIntOrDefault(args.get("x"), 0),
        Utils.parseIntOrDefault(args.get("y"), 0)), port);
  }

  public Beacon newBeacon(final Map<String, String> args) {
    return new Beacon(new Position(Utils.parseIntOrDefault(args.get("x"), 0),
        Utils.parseIntOrDefault(args.get("y"), 0)), port);
  }

  protected final Port<Satellite> port;
}
