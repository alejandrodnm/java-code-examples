package codeexamples.designpatterns.behavioural.chainofresponsibility;

public abstract class Middleware {
  private Middleware next;

  public Middleware linkWith(final Middleware next) {
    return this.next = next;
  }

  public abstract boolean check(final String p0);

  public boolean checkNext(final String text) {
    return this.next == null || this.next.check(text);
  }
}
