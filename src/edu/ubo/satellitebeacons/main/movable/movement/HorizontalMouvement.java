package edu.ubo.satellitebeacons.main.movable.movement;

public class HorizontalMouvement extends StackMovement {
  
  public HorizontalMouvement(final int min, final int max, final int speed) {
    super(new LeftMovement(min, speed), new RightMovement(max, speed));
  }

}
