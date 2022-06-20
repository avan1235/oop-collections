package pl.edu.mimuw.collections;

import java.util.*;

public class MultiMap<K, V> implements IMultimap<K, V> {
  private int amountOfLinks;
  private final Map<K, Set<V>> map;

  public MultiMap() {
    this.map = new HashMap<>();
  }


  @Override
  public int size() {
    return this.amountOfLinks;
  }

  @Override
  public boolean containsKey(K key) {
    return this.map.containsKey(key);
  }

  @Override
  public boolean containsValue(V value) {
    for (Set<V> set : this.map.values())
      for (V candidate : set) {
        if (candidate.equals(value))
          return true;
      }
    return false;
  }

  @Override
  public boolean put(K key, V value) {
    // I assumed we wanted the information about the value being in the multimap under that key previous to our putting
    // In this case true means we added another value under that key.
    Set<V> set = this.map.get(key);
    if (set == null) {
      HashSet<V> res = new HashSet<>();
      res.add(value);
      this.map.put(key, res);
      this.amountOfLinks++;
      return true;
    } else {
      if (set.contains(value)) {
        return false;
      } else {
        set.add(value);
        this.amountOfLinks++;
        return true;
      }
    }
  }

  @Override
  public boolean remove(K key, V value) {
    var set = this.map.get(key);
    if (set == null || !set.contains(value))
      return false;
    set.remove(value);
    this.amountOfLinks--;
    if (set.size() == 0)
      this.map.remove(key);
    return true;
  }

  @Override
  public boolean putAll(K key, Iterable<? extends V> values) {
    var set = this.map.get(key);
    if (set == null) {
      HashSet<V> resSet = new HashSet<>();
      for (V value: values) {
        resSet.add(value);
      }
      this.map.put(key, resSet);
      return false;
    }
    for (V el : values) {
      if (!set.contains(el)) {
        set.add(el);
        this.amountOfLinks++;
      }
    }
    return true;
  }

  @Override
  public Collection<V> removeAll(K key) {
    var res = this.map.get(key);
    if (res != null) {
      this.map.remove(key);
      this.amountOfLinks-=res.size();
    }
    return res;
  }

  @Override
  public Collection<V> get(K key) {
    return this.map.get(key);
  }

  @Override
  public Set<K> keySet() {
    return this.map.keySet();
  }

  @Override
  public IMultiset<K> keys() {
    MultiSet<K> res = new MultiSet<>();
    var keys = this.keySet();
    for (K key : keys) {
      res.add(key, this.get(key).size());
    }
    return res;
  }


  @Override
  public boolean equals(Object obj) {
    if ((obj == null) || (this.getClass() != obj.getClass()))
      return false;
    MultiMap<K, V> otherMap = (MultiMap<K, V>) obj;
    if (this.amountOfLinks != otherMap.amountOfLinks)
      return false;
    var keyset = this.keySet();
    for (K key : keyset) {
      var set1 = this.get(key);
      var set2 = otherMap.get(key);
      if (set1.size() != set2.size())
        return false;
      for (V value : set1)
        if (!set2.contains(value))
          return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder("(");
    var keyset = this.keySet();
    for (K key : keyset) {
      StringBuilder acc = new StringBuilder("{" + key.toString() + ": [");
      var valuesUnderKey = this.get(key);
      for (V value : valuesUnderKey) {
        acc.append(value + ", ");
      }
      acc.replace(acc.length()-2, acc.length(), "]}, ");
      res.append(acc);
    }
    res.replace(res.length()-2, res.length(), ")");
    return res.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.map, this.amountOfLinks);
  }

}
