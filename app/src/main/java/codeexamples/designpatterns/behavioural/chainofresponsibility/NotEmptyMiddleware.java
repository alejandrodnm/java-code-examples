package codeexamples.designpatterns.behavioural.chainofresponsibility;

public class NotEmptyMiddleware extends Middleware {
  @Override
  public boolean check(final String text) {
    return !text.isEmpty() && this.checkNext(text);
  }
}
