package pl.edu.mimuw.collections;

import java.util.*;

public class MultiSet<E> implements IMultiset<E>, Iterable<E> {

  private static final int MAX_VISIBLE_SIZE = 10;

  private final Map<E, Integer> elements;

  public MultiSet() {
    this.elements = new HashMap<>();
  }

  @Override
  public int size() {
    int result = 0;
    for (var v : this.elements.values()) {
      result += v;
    }
    return result;
  }

  @Override
  public int count(E element) {
    if (this.elements.get(element) == null) return 0;
    return this.elements.get(element);
  }

  @Override
  public int add(E element, int occurrences) {
    if (this.elements.get(element) == null) {
      this.elements.put(element, occurrences);
      return occurrences;
    }
    int tmp = this.elements.get(element);
    this.elements.remove(element);
    this.elements.put(element, tmp + occurrences);
    return tmp + occurrences;
  }

  @Override
  public boolean add(E element) {
    if (this.elements.get(element) == null) {
      this.elements.put(element, 0);
    }
    int tmp = this.elements.get(element);
    this.elements.remove(element);
    this.elements.put(element, tmp + 1);
    return true;
  }

  @Override
  public int remove(E element, int occurrences) {
    int tmp = this.elements.get(element);
    this.elements.remove(element);
    if (tmp > occurrences) this.elements.put(element, tmp - occurrences);
    return tmp - occurrences;
  }

  @Override
  public boolean remove(E element) {
    int tmp = this.elements.get(element);
    this.elements.remove(element);
    if (tmp > 1) this.elements.put(element, tmp - 1);
    return true;
  }

  @Override
  public Set<E> elementSet() {
    Set<E> result = new HashSet<>();
    for (var v : this.elements.keySet()) {
      for (int i = 0; i < this.elements.get(v); i++) {
        result.add(v);
      }
    }
    return result;
  }

  @Override
  public boolean contains(E element) {
    if (this.elements.get(element) != null) return true;
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      final Iterator<E> iterator = elements.keySet().iterator();
      E element = iterator.next();
      int counter = 0;

      @Override
      public boolean hasNext() {
        return elements.get(element) > counter || iterator.hasNext();
      }

      @Override
      public E next() {
        if (elements.get(element) == counter) {
          element = iterator.next();
          counter = 0;
        }
        counter++;
        return element;
      }
    };
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (this.size() <= MAX_VISIBLE_SIZE) {
      for (var v : this) {
        sb.append(v).append(" ");
      }
    } else {
      for (var v : this.elements.keySet()) {
        sb.append(v).append(" ").append(this.elements.get(v)).append("\n");
      }
    }
    return sb.toString();
  }


}
