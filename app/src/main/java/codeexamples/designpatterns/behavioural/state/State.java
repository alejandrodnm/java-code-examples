package codeexamples.designpatterns.behavioural.state;

public interface State {
  void moveToState(final Machine p0);

  public static class Machine {
    private State state;

    public Machine() {
      this.state = InitialState.getInstance();
    }

    public void setState(final State state) {
      this.state = state;
    }

    public String getState() {
      return this.state.getClass().getName();
    }

    public void somethingChangeTheState() {
      this.state.moveToState(this);
    }
  }

  public static class InitialState implements State {
    private static InitialState instance;

    @Override
    public void moveToState(final Machine m) {
      m.setState(MiddleState.getInstance());
    }

    public static InitialState getInstance() {
      if (InitialState.instance == null) {
        InitialState.instance = new InitialState();
      }
      return InitialState.instance;
    }
  }

  public static class MiddleState implements State {
    private static MiddleState instance;

    @Override
    public void moveToState(final Machine m) {
      m.setState(EndState.getInstance());
    }

    public static MiddleState getInstance() {
      if (MiddleState.instance == null) {
        MiddleState.instance = new MiddleState();
      }
      return MiddleState.instance;
    }
  }

  public static class EndState implements State {
    private static EndState instance;

    @Override
    public void moveToState(final Machine m) {}

    public static EndState getInstance() {
      if (EndState.instance == null) {
        EndState.instance = new EndState();
      }
      return EndState.instance;
    }
  }
}
