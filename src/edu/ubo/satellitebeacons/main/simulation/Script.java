package edu.ubo.satellitebeacons.main.simulation;

import edu.ubo.satellitebeacons.main.commands.Context;
import edu.ubo.satellitebeacons.main.commands.constructors.SatelliteConstructor;
import edu.ubo.satellitebeacons.main.event.chanel.Port;
import edu.ubo.satellitebeacons.main.movable.Satellite;

public class Script {

  public static void main(String[] args) throws Exception {
    final Port<Satellite> port = new Port<>();
    final Context ctx = new Context(System.err, System.in);
    ctx.addGlobal("PWD", System.getProperty("user.dir"));
    ctx.addClass(Satellite.class, new SatelliteConstructor(port));
    ctx.call();
  }

}
