package edu.ubo.satellitebeacons.main.simulation;

import edu.ubo.satellitebeacons.main.commands.context.Context;

public class Interpretor {

  public static void main(String[] args) throws Exception {
    final Context ctx = args.length == 0 ? new Context() : new Context(args[0]);
    ctx.call();
  }

}
