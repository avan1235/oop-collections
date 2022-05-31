package pl.edu.mimuw.collections;

import java.util.*;

//Konstruktory
public class MultiMap<K, V> implements IMultimap<K, V> {
  private int size;
  private HashMap<K, HashSet<V>> map; //impelmentacja przy pomocy hashmapy jako wartosc trzymajacej hashset typu V

  public MultiMap() {
    this.map = new HashMap<>();
    this.size = 0;
  }

  @Override
  public int size() {
    return size;
  } //wykorzystanie struktury

  @Override
  public boolean containsKey(K key) {
    return map.containsKey(key);
  } //wykorzystanie struktury

  @Override
  public boolean containsValue(V value) {
    for (var valueSet : map.values()) { //sprawdzenie mozliwych zbiorow bedacych wartosciami mapy
      if (valueSet.contains(value)) return true;
    }
    return false;
  }

  @Override
  public boolean put(K key, V value) {
    if (map.isEmpty() || !map.containsKey(key)) { //W tym przypadku inicjalizacja zbioru jednoelementowego i dodanie pary klucz-zbior jednoelementowy
      this.size++;
      var values = new HashSet<V>(1);
      values.add(value);
      map.put(key, values);
      return true;
    }
    map.get(key).add(value); //w przeciwnym przypadku dodanie wartosci do zbioru przy odpowiednimkluczu
    return false;
  }

  @Override
  public boolean remove(K key, V value) {
    if (!map.containsKey(key)) return false;
    var tempSet = map.get(key);
    if (tempSet.contains(value)) { //w przypadku gdy element wystepuje: usuniecie go z odpowiedniego zbioru
      tempSet.remove(value);
      if (tempSet.size() == 0) {
        map.remove(key);
        this.size--;
      } else map.put(key, tempSet);
      return true;
    }
    return false;
  }

  @Override
  public boolean putAll(K key, Iterable<? extends V> values) {
    if (!this.map.containsKey(key)) {
      var valueSet = new HashSet<V>(1);
      for (var value : values) { //wykorzystanie iterowalnosci values do przepisania jego zawartosci do zbioru
        valueSet.add((value));
      }
      map.put(key, valueSet);
    }
    for (var value : values) { //wykorzystanie iterowalnosci values do przepisania jego zawartosci do zbioru
      this.size++;
    }
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MultiMap<?, ?> multiMap = (MultiMap<?, ?>) o;
    return size == multiMap.size && Objects.equals(map, multiMap.map);
  }

  @Override
  public int hashCode() {
    return Objects.hash(size, map);
  }

  @Override
  public Collection<V> removeAll(K key) { //usuniecie klucza ze wszystkimi wartosciami a nastepnie zwrocenie ich zbioru
    if (containsKey(key)) {
      var occurrences = new HashSet<>(this.map.get(key));
      map.remove(key);
      this.size--;
      return occurrences;
    }
    return new HashSet<>();
  }

  @Override
  public Collection<V> get(K key) {
    return this.map.get(key);
  }

  @Override
  public Set<K> keySet() {
    return new HashSet<>(this.map.keySet());
  }

  @Override
  public IMultiset<K> keys() {
    return new MultiSet<K>(this.keySet());
  } //wykorzystaie konstruktora MultiSet

  @Override
  public String toString() {
    var sb = new StringBuilder();
    sb.append("Multimap with entries:\n");
    for (var key : this.map.keySet()) {
      sb.append(key.toString()).append(": mapped to set: {\n");
      for (var elem : this.map.get(key)) {
        sb.append(elem.toString()).append('\n');
      }
      sb.append('}').append('\n');
    }
    return sb.toString();
  }
}
