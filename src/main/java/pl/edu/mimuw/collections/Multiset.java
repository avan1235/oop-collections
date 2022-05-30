package pl.edu.mimuw.collections;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Multiset<E> implements IMultiset<E> {
  private final static int MAX_WHOLE_PRINT = 80;
  private final Map<E, Integer> elemCount;
  private int size;

  public Multiset() {
    this.elemCount = new HashMap<>();
    this.size = 0;
  }

  public int size() {
    return size;
  }

  public int count(E element) {
    return elemCount.getOrDefault(element, 0);
  }

  public int add(E element, int occurrences) {
    if (occurrences < 0) throw new IllegalArgumentException();
    int prev = count(element);
    if (occurrences == 0) return prev;
    this.elemCount.put(element, prev + occurrences);
    size += occurrences;
    return prev;
  }

  public boolean add(E element) {
    return add(element, 1) == 0;
  }

  public int remove(E element, int occurrences) {
    if (occurrences < 0) throw new IllegalArgumentException();
    int prev = count(element);
    if (occurrences == 0) return prev;
    this.elemCount.put(element, Math.max(0, prev - occurrences));
    size -= Math.min(prev, occurrences);
    return prev;
  }

  public boolean remove(E element) {
    return remove(element, 1) > 0;
  }

  public Set<E> elementSet() {
    return new HashSet<>(this.elemCount.keySet());
  }

  public boolean contains(E element) {
    if (!this.elemCount.containsKey(element)) return false;
    return this.elemCount.get(element) != 0;
  }

  public Iterator<E> iterator() {
    List<E> res = new LinkedList<>();

    for (E e : this.elemCount.keySet())
      for (int i = 0; i < count(e); i++)
        res.add(e);

    return res.iterator();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Multiset<?> multiset = (Multiset<?>) o;
    return size == multiset.size && elemCount.equals(multiset.elemCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(elemCount, size);
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    Iterator<E> iterator = size() > MAX_WHOLE_PRINT ? this.elemCount.keySet().iterator() : iterator();
    AtomicInteger i = new AtomicInteger(0);

    res.append("{");
    iterator.forEachRemaining(e -> {
      String front = "", end = "";
      if (size() > MAX_WHOLE_PRINT) {
        front = count(e) + "x \"";
        end = "\"";
      }
      if (i.incrementAndGet() > 1) res.append(", ");
      res.append(front).append(e).append(end);
    });
    res.append("}");

    return res.toString();
  }
}
