package codeexamples.designpatterns.behavioural.observer;

import java.util.LinkedList;
import java.util.List;

public interface Observable {
  void subscribe(final Observer p0);

  void unsubscribe(final Observer p0);

  public static class MyObservable implements Observable {
    private final List<Observer> observers;
    private final List<String> messages;

    public MyObservable() {
      this.observers = new LinkedList<Observer>();
      this.messages = new LinkedList<String>();
    }

    @Override
    public void subscribe(final Observer o) {
      this.observers.add(o);
    }

    @Override
    public void unsubscribe(final Observer o) {
      this.observers.remove(o);
    }

    public void onMessage(final String msg) {
      this.messages.add(msg);
      for (final Observer o : this.observers) {
        o.update();
      }
    }
  }
}
