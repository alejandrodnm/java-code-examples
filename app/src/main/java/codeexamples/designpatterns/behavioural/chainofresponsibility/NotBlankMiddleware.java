package codeexamples.designpatterns.behavioural.chainofresponsibility;

public class NotBlankMiddleware extends Middleware {
  @Override
  public boolean check(final String text) {
    return !text.isBlank() && this.checkNext(text);
  }
}
