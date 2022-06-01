package pl.edu.mimuw.collections;

import java.util.*;

public class Multiset<E> implements IMultiset<E> {
  private final Map<E, Integer> multiset;

  public Multiset() {
    this.multiset = new HashMap<>();
  }

  public int getOccurrences(E element) {
    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      if (entry.getKey().equals(element)) {
        return entry.getValue();
      }
    }
    return 0;
  }

  @Override
  public int size() {
    int size = 0;
    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      size += entry.getValue();
    }
    return size;
  }

  @Override
  public int count(E element) {
    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      if (entry.getKey().equals(element)) {
        return entry.getValue();
      }
    }
    return 0;
  }

  @Override
  public int add(E element, int occurrences) {
    if (occurrences < 1) throw new IllegalArgumentException("Invalid occurrence count.");

    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      if (entry.getKey().equals(element)) {
        entry.setValue(entry.getValue() + occurrences);
        return entry.getValue();
      }
    }
    this.multiset.put(element, occurrences);
    return occurrences;
  }

  @Override
  public boolean add(E element) {
    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      if (entry.getKey().equals(element)) {
        entry.setValue(entry.getValue() + 1);
        return false;
      }
    }

    this.multiset.put(element, 1);
    return true;
  }

  @Override
  public int remove(E element, int occurrences) {
    if (occurrences < 1) throw new IllegalArgumentException("Invalid occurrence count.");

    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      if (entry.getKey().equals(element)) {
        if (entry.getValue() - occurrences > 0) {
          entry.setValue(entry.getValue() - occurrences);
        }
        else {
          multiset.remove(element);
        }

        return entry.getValue();
      }
    }
    return 0;
  }

  @Override
  public boolean remove(E element) {
    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      if (entry.getKey().equals(element)) {
        this.multiset.remove(element);
        return true;
      }
    }
    return false;
  }

  @Override
  public Set<E> elementSet() {
    Set<E> elementSet = new HashSet<>();
    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      elementSet.add(entry.getKey());
    }
    return elementSet;
  }

  @Override
  public boolean contains(E element) {
    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      if (entry.getKey().equals(element)) {
        return true;
      }
    }
    return false;
  }

  public Collection<E> toCollection() {
    List<E> newList = new ArrayList<>();
    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      for (int i = 0; i < entry.getValue(); i++) {
        newList.add(entry.getKey());
      }
    }
    return newList;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      private final Iterator<E> iterator = multiset.keySet().iterator();

      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public E next() {
        return iterator.next();
      }
    };
  }

  public Map<E, Integer> getMultiset() {
    return this.multiset;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<E, Integer> entry: multiset.entrySet()) {
      sb.append("key: ").append(entry.getKey()).append(", occurrences: ")
        .append(entry.getValue()).append("\n");
    }
    return sb.toString();
  }

}
