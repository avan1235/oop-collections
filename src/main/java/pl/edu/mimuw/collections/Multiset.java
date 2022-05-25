package pl.edu.mimuw.collections;

import java.util.*;

public class Multiset<E> implements IMultiset<E> {
  private final Map<E, Integer> countOfKey;

  public Multiset() {
    this.countOfKey = new HashMap<E, Integer>();
  }

  @Override
  public int size() {
    return countOfKey.values().stream().mapToInt(x -> x).sum();
  }

  @Override
  public int count(E element) {
    return countOfKey.getOrDefault(element, 0);
  }

  @Override
  public int add(E element, int occurrences) {
    if (occurrences < 0) throw new IllegalArgumentException("Can't remove a negative amount.");
    var x = count(element);
    if (occurrences == 0) return x;
    countOfKey.put(element, x + occurrences);
    return x;
  }

  @Override
  public boolean add(E element) {
    return add(element, 1) == 0;
  }

  @Override
  public int remove(E element, int occurrences) {
    if (occurrences < 0) throw new IllegalArgumentException("Can't remove a negative amount.");
    var x = count(element);
    if (occurrences == 0) return x;
    var n = Math.max(x - occurrences, 0);
    if (n == 0) countOfKey.remove(element);
    else countOfKey.put(element, n);
    return x;
  }

  @Override
  public boolean remove(E element) {
    return remove(element, 1) > 0;
  }

  @Override
  public Set<E> elementSet() {
    return countOfKey.keySet();
  }

  @Override
  public boolean contains(E element) {
    return count(element) > 0;
  }

  @Override
  public Iterator<E> iterator() {
    return countOfKey.entrySet().stream()
        .flatMap(entry -> Collections.nCopies(entry.getValue(), entry.getKey()).stream())
        .iterator();
  }
}
