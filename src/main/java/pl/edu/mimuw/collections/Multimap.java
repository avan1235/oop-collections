package pl.edu.mimuw.collections;

import java.util.*;

public class Multimap<K, V> implements IMultimap<K, V> {
  private final Map<K, Multiset<V>> valuesOfKey;

  public Multimap() {
    valuesOfKey = new HashMap<>();
  }

  @Override
  public int size() {
    return valuesOfKey.entrySet().stream()
        .map(entry -> entry.getValue().size())
        .mapToInt(x -> x)
        .sum();
  }

  @Override
  public boolean containsKey(K key) {
    return valuesOfKey.containsKey(key);
  }

  @Override
  public boolean containsValue(V value) {
    return false;
  }

  @Override
  public boolean put(K key, V value) {
    return false;
  }

  @Override
  public boolean remove(K key, V value) {
    return false;
  }

  @Override
  public boolean putAll(K key, Iterable<? extends V> values) {
    values.forEach(x -> put(key, x));
  }

  @Override
  public Collection<V> removeAll(K key) {
    return null;
  }

  @Override
  public Collection<V> get(K key) {
    return newArrayList valuesOfKey.getOrDefault(key, new Multiset<>()).iterator();
  }

  @Override
  public Set<K> keySet() {
    return null;
  }

  @Override
  public IMultiset<K> keys() {
    return null;
  }
}
