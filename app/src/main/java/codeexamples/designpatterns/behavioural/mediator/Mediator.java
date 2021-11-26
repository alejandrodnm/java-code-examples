package codeexamples.designpatterns.behavioural.mediator;

import java.util.LinkedList;
import java.util.List;

public class Mediator {
  public final Mediated mediated1;
  public final Mediated mediated2;

  public Mediator() {
    this.mediated1 = new Mediated(this);
    this.mediated2 = new Mediated(this);
  }

  public void notify(final Mediated m, final String msg) {
    if (m.equals(this.mediated1)) {
      this.mediated2.recv(msg);
      return;
    }
    this.mediated1.recv(msg);
  }

  public static class Mediated {
    private Mediator mediator;
    public final List<String> msgs;

    public Mediated(final Mediator mediator) {
      this.msgs = new LinkedList<String>();
      this.mediator = mediator;
    }

    public void notify(final String msg) {
      this.mediator.notify(this, msg);
    }

    public void recv(final String msg) {
      this.msgs.add(msg);
    }
  }
}
