package pl.edu.mimuw.collections;

import java.util.*;

public class CollMultiMap<K, V> implements IMultimap<K, V> {

  private final HashMap<K, CollMultiset<V>> map;

  public CollMultiMap() {
    this.map = new HashMap<>();
  }

  @Override
  public int size() {
    int sum = 0;
    for (K key : map.keySet()) {
      sum += map.get(key).size();
    }
    return sum;
  }

  @Override
  public boolean containsKey(K key) {
    return map.containsKey(key);
  }

  @Override
  public boolean containsValue(V value) {
    for (K key : map.keySet()) {
      if (map.get(key).contains(value))
        return true;
    }
    return false;
  }

  public boolean containsKeyValue(K key, V value) {
    for (K key2 : map.keySet()) {
      if (key2.equals(key)) {
        if (map.get(key2).contains(value))
          return true;
      }
    }
    return false;
  }

  @Override
  public boolean put(K key, V value) {
    boolean result = !containsKeyValue(key, value);
    if (!map.containsKey(key))
      map.put(key, new CollMultiset<V>());
    map.get(key).add(value);
    return result;
  }

  @Override
  public boolean remove(K key, V value) {
    if (map.containsKey(key)) {
      map.get(key).remove(value);
    }
    return !containsKeyValue(key, value);
  }

  @Override
  public boolean putAll(K key, Iterable<? extends V> values) {
    boolean result = false;
    for (V value : values) {
      result = result || put(key, value);
    }
    return result;
  }

  @Override
  public Collection<V> removeAll(K key) {
    Collection<V> output = get(key);
    map.remove(key);
    return output;
  }

  @Override
  public Collection<V> get(K key) {
    ArrayList<V> output = null;
    if (map.containsKey(key)) {
      output = new ArrayList<>(map.get(key).size());
      //Nie rozumiem czemu ten Iterator nie działa normalnie.
      for (Iterator<V> it = map.get(key).iterator(); it.hasNext(); )
        output.add(it.next());
    }
    return output;
  }

  @Override
  public Set<K> keySet() {
    return map.keySet();
  }

  @Override
  public IMultiset<K> keys() {
    IMultiset<K> output = new CollMultiset<>();
    for(K key : map.keySet()) {
      output.add(key, map.get(key).size());
    }
    return output;
  }
}
