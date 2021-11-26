package codeexamples.designpatterns.behavioural.command;

public class CommandHandler {
  private Store<String, String> store;

  public CommandHandler() {
    this.store = new HashMapStore<String, String>();
  }

  public String executeCommand(final Command c) {
    return c.query(this.store);
  }
}
