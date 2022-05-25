package pl.edu.mimuw.collections;

import java.util.Collection;
import java.util.Set;

public interface IMultimap<K, V> {

  int size();

  boolean containsKey(K key);

  boolean containsValue(V value);

  boolean put(K key, V value);

  boolean remove(K key, V value);

  boolean putAll(K key, Iterable<? extends V> values);

  Collection<V> removeAll(K key);

  Collection<V> get(K key);

  Set<K> keySet();

  IMultiset<K> keys();

  @Override
  boolean equals(Object obj);

  @Override
  int hashCode();

  @Override
  String toString();
}

