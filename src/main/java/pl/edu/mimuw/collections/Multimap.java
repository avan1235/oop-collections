package pl.edu.mimuw.collections;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Multimap<K, V> implements IMultimap<K, V> {
  private final Map<K, Set<V>> map;
  private int size;

  public Multimap() {
    this.map = new HashMap<>();
    this.size = 0;
  }

  public int size() {
    return size;
  }

  public boolean containsKey(K key) {
    if (!this.map.containsKey(key)) return false;
    return !this.map.get(key).isEmpty();
  }

  public boolean containsValue(V value) {
    for (Set<V> valueSet : this.map.values())
      if (valueSet.contains(value)) return true;
    return false;
  }

  public boolean put(K key, V value) {
    if (!this.map.containsKey(key)) {
      Set<V> valueSet = new HashSet<>();
      this.map.put(key, valueSet);
    }
    if (this.map.get(key).add(value)) {
      this.size++;
      return true;
    }
    return false;
  }

  public boolean remove(K key, V value) {
    if (!this.map.containsKey(key)) return false;

    if (this.map.get(key).remove(value)) {
      this.size--;
      return true;
    }
    return false;
  }

  public boolean putAll(K key, Iterable<? extends V> values) {
    AtomicBoolean res = new AtomicBoolean(false);
    values.forEach(e -> res.set(put(key, e) || res.get()));
    return res.get();
  }

  public Collection<V> removeAll(K key) {
    if (!this.map.containsKey(key)) return new HashSet<>();
    Collection<V> res = Set.copyOf(this.map.get(key));
    this.map.remove(key);
    this.size -= res.size();
    return res;
  }

  public Collection<V> get(K key) {
    return this.map.get(key);
  }

  public Set<K> keySet() {
    return this.map.keySet();
  }

  public IMultiset<K> keys() {
    Multiset<K> multiset = new Multiset<>();

    for (K key : this.map.keySet())
      multiset.add(key, this.map.get(key).size());

    return multiset;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Multimap<?, ?> multimap = (Multimap<?, ?>) o;
    return size == multimap.size && map.equals(multimap.map);
  }

  @Override
  public int hashCode() {
    return Objects.hash(map, size);
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder("{");
    int i = 0;

    for (K key : keySet()) {
      if (i++ > 0) res.append(", ");
      res.append(key).append(" -> ").append(get(key));
    }
    res.append("}");

    return res.toString();
  }
}
