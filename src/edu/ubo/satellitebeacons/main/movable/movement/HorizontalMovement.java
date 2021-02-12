package edu.ubo.satellitebeacons.main.movable.movement;

import edu.ubo.satellitebeacons.main.annotations.ScriptClass;

@ScriptClass("BeaconMovement")
public class HorizontalMovement extends StackMovement {
  
  public HorizontalMovement(final int min, final int max, final int speed) {
    super(new LeftMovement(min, speed));
    this.setMovements(new RightMovement(max, speed));
  }

}
