package edu.ubo.satellitebeacons.main.observable;

/**
 * Represent an observable object
 * 
 * @param <T> Target
 */
public interface Observable<T> {
  
  /**
   * Add a new observer
   * @param observer An observer
   */
  void addObserver(Observer<T> observer);
  
  /**
   * Delete an observer
   * @param observer An observer to delete
   */
  void deleteObserver(Observer<T> observer);
  
  /**
   * Delete all the observers
   */
  void deleteObservers();
  
  /**
   * Notify all the observers
   * @param target
   */
  void notifyObservers(T target);
  
  /**
   * Returns the number of observers
   * @return The number of observers
   */
  int countObservers();
}
