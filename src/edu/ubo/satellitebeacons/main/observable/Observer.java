package edu.ubo.satellitebeacons.main.observable;

/**
 * Represent an observer
 *
 * @param <T>
 */
public interface Observer<T> {
  
  /**
   * Method to be call if the observer are updated.
   * @param observer Observer to be update
   * @param target 
   */
  void update(Observer<T> observer, T target);

}
