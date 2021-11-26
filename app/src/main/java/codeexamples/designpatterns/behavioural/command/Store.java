package codeexamples.designpatterns.behavioural.command;

public interface Store<K, V> {
  V get(final K p0);

  void put(final K p0, final V p1);
}
