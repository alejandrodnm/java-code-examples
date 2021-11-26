package codeexamples.designpatterns.behavioural.command;

public class GetCommand implements Command {
  private String key;

  public GetCommand(final String key) {
    this.key = key;
  }

  @Override
  public String query(final Store<String, String> store) {
    return store.get(this.key);
  }
}
