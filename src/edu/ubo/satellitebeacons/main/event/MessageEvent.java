package edu.ubo.satellitebeacons.main.event;

import java.util.EventObject;

public class MessageEvent<T> extends EventObject {
  private static final long serialVersionUID = 1735209822722421835L;

  public MessageEvent(final Object source, final T content) {
    super(source);
    this.content = content;
  }
  
  public T getContent() {
    return content;
  }

  protected final transient T content;

}
