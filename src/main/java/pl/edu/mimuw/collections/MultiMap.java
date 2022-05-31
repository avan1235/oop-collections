package pl.edu.mimuw.collections;

import java.util.*;

public class MultiMap<K, V> implements IMultimap<K, V> {

  private final Map<K, Multiset<V>> map;

  public MultiMap() {
    this.map = new HashMap<>();
  }

  @Override
  public int size() {
    int numberOfElements = 0;
    for (K i : this.map.keySet()) {
      numberOfElements += this.map.get(i).size();
    }

    return numberOfElements;
  }

  @Override
  public boolean containsKey(K key) {
    return this.map.containsKey(key);
  }

  @Override
  public boolean containsValue(V value) {
    for (var key : keySet()) {
      if (map.get(key).contains(value)) return true;
    }
    return false;
  }

  @Override
  public boolean put(K key, V value) {
    if (!this.map.containsKey(key)) {
      this.map.put(key, new Multiset<>());
    }
    this.map.get(key).add(value);
    return true;
  }

  @Override
  public boolean remove(K key, V value) {
    if (!this.map.containsKey(key)) return false;
    var values = this.map.get(key);
    values.remove(value);
    if (values.size() == 0) this.map.remove(key);
    return true;
  }

  @Override
  public boolean putAll(K key, Iterable<? extends V> values) {
    for (var i : values) {
      put(key, i);
    }
    return true;
  }

  @Override
  public Collection<V> removeAll(K key) {
    if (!this.map.containsKey(key)) return new HashSet<>();
    var result = this.get(key);
    this.map.remove(key);
    return result;
  }

  @Override
  public Collection<V> get(K key) {
    var set = this.map.get(key);
    var coll = new ArrayList<V>();
    set.iterator().forEachRemaining(coll::add);
    return coll;
  }

  @Override
  public Set<K> keySet() {
    return this.map.keySet();
  }

  @Override
  public IMultiset<K> keys() {
    var set = new Multiset<K>();

    for (var key : this.map.keySet()) {
      set.add(key, this.map.get(key).size());
    }

    return set;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    MultiMap<?, ?> multi = (MultiMap<?, ?>) obj;
    return this.map.equals(multi.map) && this.size() == multi.size();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.map, this.size());
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("MultiMap:\n");
    result.append("{ ");

    for (var key : this.keySet()) {
      result.append(key)
        .append(" - ")
        .append(this.get(key))
        .append(", ");
    }
    result.append(" }");

    return result.toString();
  }
}
