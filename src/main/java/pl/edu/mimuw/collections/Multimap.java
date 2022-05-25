package pl.edu.mimuw.collections;


import java.util.*;
import java.util.stream.Collectors;


public class Multimap<K, V> implements IMultimap<K, V> {
  private int size;
  private Map<K, Multiset<V>> elements;

  public Multimap() {
    this.elements = new HashMap<K, Multiset<V>>();
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean containsValue(V value) {
    for (K key : this.elements.keySet()) {
      if (this.elements.get(key).contains(value))
        return true;
    }
    return false;
  }

  //returns set of key, each key appears at most once
  @Override
  public Set<K> keySet() {
    return this.elements.keySet();
  }

  //returns multiset of keys, where key occurs as many times
  //as many values are assigned to it
  @Override
  public IMultiset<K> keys() {
    var result = new Multiset<K>();
    for (K key : this.keySet()) {
      result.add(key, this.elements.get(key).size());
    }
    return result;
  }

  @Override
  public Collection get(K key) {
    var result = new ArrayList<V>();
    if (this.elements.containsKey(key)) {
      Iterator<V> it = this.elements.get(key).iterator();
      while (it.hasNext()) {
        result.add(it.next());
      }
    }
    return result;
  }

  @Override
  public Collection removeAll(K key) {
    Collection<V> result = get(key);
    this.size -= this.get(key).size();
    this.elements.remove(key);
    return result;
  }

  @Override
  public boolean putAll(K key, Iterable<? extends V> values) {
    var result = false;
    for (V value : values) {
      this.size++;
      result = true;
      this.put(key, value);
    }
    return result;
  }

  //removes all elements of type <key, value>
  @Override
  public boolean remove(K key, V value) {
    if (!this.elements.containsKey(key))
      return false;
    return this.elements.get(key).remove(value);
  }

  @Override
  public boolean put(K key, V value) {
    if (!this.elements.containsKey(key))
      this.elements.put(key, new Multiset<>());
    this.elements.get(key).add(value);
    this.size++;
    return true;
  }

  @Override
  public boolean containsKey(K key) {
    return this.elements.containsKey(key);
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();
    sb.append("This object is a map containing following keys and values\n");
    for (K key : this.keySet()) {
      sb.append("Key ").append(key).append(" with values: ");
      Iterator<V> it = this.elements.get(key).iterator();
      while (it.hasNext()) {
        sb.append(it.next()).append(", ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public int hashCode() {
    return Objects.hash(this.elements);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o.getClass() == Multimap.class))
      return false;
    if (!((Multimap<?, ?>) o).keySet().equals(this.keySet()))
      return false;

    for (K key1 : this.keySet()) {
      var result1 = this.elements.get(key1);
      var result2 = new Multiset<V>();
      for(K key2: ((Multimap<K, ?>) o).keySet()){
        if(key1.equals(key2)){
          result2=((Multimap<K, V>) o).elements.get(key2);
          if(!result1.equals(result2))
            return false;
        }
      }
    }
    return true;
  }

}
