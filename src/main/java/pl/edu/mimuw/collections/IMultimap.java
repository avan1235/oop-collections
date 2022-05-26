package pl.edu.mimuw.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public interface IMultimap<K, V> extends Iterable<Map.Entry<K, V>> {

  int size();

  boolean containsKey(K key);

  boolean containsValue(V value);

  // These semantics were copied from the Guava multimap, assuming that multiple identical (key,
  // value) pairs may exist in the same map at the same time.

  /**
   * @return Has the map changed? <i>Always true.</i>
   */
  boolean put(K key, V value);

  /**
   * Remove a <i>single</i> occurrence of the (key, value) pair.
   *
   * @return Has the map changed?
   */
  boolean remove(K key, V value);

  /**
   * @return Has the map changed? <i>Always true.</i>
   */
  boolean putAll(K key, Iterable<? extends V> values);

  /**
   * @return The values that were removed.
   */
  Collection<V> removeAll(K key);

  Collection<V> get(K key);

  Set<K> keySet();

  /**
   * Get all the keys in the map. With duplicates for any duplicate keys. I can't imagine what it
   * would be useful for, but I don't know why the return type would be a multiset if duplicates
   * weren't necessary.
   */
  IMultiset<K> keys();

  Iterator<Map.Entry<K, V>> iterator();

  Stream<Map.Entry<K, V>> stream();

  @Override
  boolean equals(Object obj);

  @Override
  int hashCode();

  @Override
  String toString();
}
