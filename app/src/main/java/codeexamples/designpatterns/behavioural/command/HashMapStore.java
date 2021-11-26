package codeexamples.designpatterns.behavioural.command;

import java.util.HashMap;
import java.util.Map;

public class HashMapStore<K, V> implements Store<K, V> {
  private Map<K, V> store;

  public HashMapStore() {
    this.store = new HashMap<K, V>();
  }

  @Override
  public V get(final K key) {
    return this.store.get(key);
  }

  @Override
  public void put(final K key, final V value) {
    this.store.put(key, value);
  }
}
