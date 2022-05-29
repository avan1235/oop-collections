package pl.edu.mimuw.collections;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ArrayMultiMap<K, V> implements IMultimap<K, V> {
  private final IMultiset<Entry<K, V>> entries;

  public ArrayMultiMap() {
    entries = new ArrayMultiSet<>();
  }

  @Override
  public int size() {
    return entries.size();
  }

  @Override
  public boolean containsKey(K key) {
    return entries.elementSet().stream().anyMatch(entry -> entry.key == key);
  }

  @Override
  public boolean containsValue(V value) {
    return entries.elementSet().stream().anyMatch(entry -> entry.value == value);
  }

  @Override
  public boolean put(K key, V value) {
    return entries.add(new Entry<>(key, value));
  }

  @Override
  public boolean remove(K key, V value) {
    return entries.remove(new Entry<>(key, value));
  }

  @Override
  public boolean putAll(K key, Iterable<? extends V> values) {
    boolean success = true;
    for (var value : values)
      success = put(key, value) && success;
    return success;
  }

  @Override
  public Collection<V> removeAll(K key) {
    return entries.removeAllMatching((entry) -> entry.key == key).stream().map((entry) -> entry.value).collect(Collectors.toList());
  }

  @Override
  public Collection<V> get(K key) {
    return entries.getAllMatching((entry) -> entry.key == key).stream().map((entry) -> entry.value).collect(Collectors.toList());
  }

  @Override
  public Set<K> keySet() {
    return entries.elementSet().stream().map((entry) -> entry.key).collect(Collectors.toSet());
  }

  @Override
  public IMultiset<K> keys() {
    return entries.map((entry) -> entry.key);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ArrayMultiMap<?, ?> that = (ArrayMultiMap<?, ?>) o;
    return entries.equals(that.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("ArrayMultiMap{");
    for (var key : keySet()) {
      builder.append(key);
      builder.append(": [");
      for (var value : get(key)) {
        builder.append(value);
        builder.append(", ");
      }
      if (get(key).size() > 0) builder.delete(builder.length() - 2, builder.length());
      builder.append("], ");
    }
    if (entries.size() > 0) builder.delete(builder.length() - 2, builder.length());
    builder.append('}');
    return builder.toString();
  }

  public static class Entry<K, V> {
    public final K key;
    public final V value;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Entry<?, ?> entry = (Entry<?, ?>) o;
      return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(key, value);
    }
  }
}
