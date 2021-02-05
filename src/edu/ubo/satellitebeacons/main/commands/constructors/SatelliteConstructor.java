package edu.ubo.satellitebeacons.main.commands.constructors;

import java.util.Map;

import edu.ubo.satellitebeacons.main.commands.Command;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.movable.Satellite;
import edu.ubo.satellitebeacons.main.space.Position;
import edu.ubo.satellitebeacons.main.utils.Utils;

public class SatelliteConstructor implements Command<Satellite> {

  public SatelliteConstructor(final Port<Satellite> port) {
    this.port = port;
  }

  @Override
  public Satellite call(final Map<String, String> args) {
    return new Satellite(new Position(Utils.parseIntOrDefault(args.get("x"), 0),
        Utils.parseIntOrDefault(args.get("y"), 0)), port);
  }

  protected final Port<Satellite> port;
}
