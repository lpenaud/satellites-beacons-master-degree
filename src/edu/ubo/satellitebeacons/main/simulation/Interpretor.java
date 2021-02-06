package edu.ubo.satellitebeacons.main.simulation;

import edu.ubo.satellitebeacons.main.commands.context.Context;

public class Interpretor {

  public static void main(String[] args) throws Exception {
    final Context ctx = new Context();
    ctx.call();
  }

}
