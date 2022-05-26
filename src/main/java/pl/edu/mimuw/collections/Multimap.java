package pl.edu.mimuw.collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// In hindsight, this should've been a multiset of entries and not a map from a key to a multiset of
// values. The code would've been much clearer.
public final class Multimap<K, V> implements IMultimap<K, V> {
  private final Map<K, Multiset<V>> valuesOfKey;

  public Multimap() {
    valuesOfKey = new HashMap<>();
  }

  public Multimap(Iterable<Map.Entry<K, V>> entries) {
    this();
    entries.forEach(entry -> put(entry.getKey(), entry.getValue()));
  }

  @Override
  public int size() {
    return valuesOfKey.values().stream().map(Multiset::size).mapToInt(x -> x).sum();
  }

  @Override
  public boolean containsKey(K key) {
    return valuesOfKey.containsKey(key);
  }

  @Override
  public boolean containsValue(V value) {
    return stream().anyMatch(entry -> entry.getValue() == value);
  }

  @Override
  public boolean put(K key, V value) {
    if (!valuesOfKey.containsKey(key)) valuesOfKey.put(key, new Multiset<>());
    valuesOfKey.get(key).add(value);
    return true;
  }

  @Override
  public boolean remove(K key, V value) {
    var values = valuesOfKey.get(key);
    if (values == null) return false;
    var hasChanged = values.remove(value);
    if (values.size() == 0) valuesOfKey.remove(key);
    return hasChanged;
  }

  @Override
  public boolean putAll(K key, Iterable<? extends V> values) {
    values.forEach(value -> put(key, value));
    return true;
  }

  @Override
  public Collection<V> removeAll(K key) {
    return valuesOfKey.remove(key).toCollection();
  }

  @Override
  public Collection<V> get(K key) {
    return valuesOfKey.getOrDefault(key, new Multiset<>()).toCollection();
  }

  @Override
  public Set<K> keySet() {
    return valuesOfKey.keySet();
  }

  @Override
  public Iterator<Map.Entry<K, V>> iterator() {
    return valuesOfKey.entrySet().stream()
        .flatMap(entry -> entry.getValue().stream().map(value -> Map.entry(entry.getKey(), value)))
        .iterator();
  }

  @Override
  public Stream<Map.Entry<K, V>> stream() {
    return Utility.toStream(this);
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object o) {
    return new Multiset<>(this).equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(valuesOfKey);
  }

  @Override
  public String toString() {
    return stream().map(String::valueOf).collect(Collectors.joining(", ", "{", "}"));
  }

  @Override
  public IMultiset<K> keys() {
    return new Multiset<>(() -> stream().map(Map.Entry::getKey).iterator());
  }
}
