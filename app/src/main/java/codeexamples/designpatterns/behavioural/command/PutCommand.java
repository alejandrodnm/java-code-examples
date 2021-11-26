package codeexamples.designpatterns.behavioural.command;

public class PutCommand implements Command {
  private String key;
  private String value;

  public PutCommand(final String key, final String value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public String query(final Store<String, String> store) {
    store.put(this.key, this.value);
    return "OK";
  }
}
