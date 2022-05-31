package pl.edu.mimuw.collections;

import java.util.*;

public class Multimap<K, V> implements IMultimap<K, V>{
  private final Map<K, Multiset<V>> multimap;

  public Multimap() {
    this.multimap = new HashMap<>();
  }

  @Override
  public int size() {
    int size = 0;
    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      Multiset<V> temp = entry.getValue();
      for (V v: temp) {
        size += temp.getOccurrences(v);
      }
    }
    return size;
  }

  @Override
  public boolean containsKey(K key) {
    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      if (entry.getKey().equals(key)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsValue(V value) {
    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      Multiset<V> temp = entry.getValue();
      for (V v: temp) {
        if (v.equals(value)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean put(K key, V value) {

    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      if (entry.getKey().equals(key)) {
        return entry.getValue().add(value);
      }
    }
    Multiset<V> newMultiset = new Multiset<>();
    newMultiset.add(value);
    multimap.put(key, newMultiset);
    return true;
  }

  @Override
  public boolean remove(K key, V value) {
    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      if (entry.getKey().equals(key)) {
        if (entry.getValue().remove(value)) {
          return true;
        }
        return false;
      }
    }
    return false;
  }

  @Override
  public boolean putAll(K key, Iterable<? extends V> values) {
    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      if (entry.getKey().equals(key)) {
        for (var x: values) {
          entry.getValue().add(x);
        }
        return false;
      }
    }
    Multiset<V> newMultiset = new Multiset<>();
    for (var x: values) {
      newMultiset.add(x);
    }
    multimap.put(key, newMultiset);
    return true;
  }

  @Override
  public Collection<V> removeAll(K key) {
    Multiset<V> newSet = new Multiset<>();
    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      if (entry.getKey().equals(key)) {
        entry.setValue(newSet);
      }
    }
    return newSet.toCollection();
  }

  @Override
  public Collection<V> get(K key) {
    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      if (entry.getKey().equals(key)) {
        return entry.getValue().toCollection();
      }
    }
    return null;
  }

  @Override
  public Set<K> keySet() {
    Set<K> newSet = new HashSet<>();
    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      newSet.add(entry.getKey());
    }
    return newSet;
  }

  @Override
  public IMultiset<K> keys() {
    Multiset<K> newSet = new Multiset<>();
    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      newSet.add(entry.getKey());
    }
    return newSet;
  }

  @Override
  public Iterator<K> iterator() {
    return new Iterator<>() {
      private final Iterator<K> iterator = multimap.keySet().iterator();

      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public K next() {
        return iterator.next();
      }
    };
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<K, Multiset<V>> entry: multimap.entrySet()) {
      sb.append("key: ").append(entry.getKey()).append(" -----------------\n");
      for (Map.Entry<V, Integer> v: entry.getValue().getMultiset().entrySet()) {
        sb.append("value: ").append(v.getKey()).append(", occurrences: ")
          .append(v.getValue()).append("\n");
      }
    }
    return sb.toString();
  }
}
