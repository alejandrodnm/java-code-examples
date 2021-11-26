package codeexamples.designpatterns.behavioural.observer;

public interface Observer {
  void update();

  public static class MyObserver implements Observer {
    public boolean observedSomething;

    public MyObserver(final Observable o) {
      this.observedSomething = false;
      o.subscribe(this);
    }

    @Override
    public void update() {
      this.observedSomething = true;
    }
  }
}
