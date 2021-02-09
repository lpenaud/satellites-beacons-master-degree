package edu.ubo.satellitebeacons.main.commands.exceptions;

public class ReferenceException extends Exception {
  private static final long serialVersionUID = 4472277535124478512L;

  public ReferenceException(final String name) {
    super(String.format("%s is not defined", name));
    this.name = name;
  }
  
  protected final String name;

}
