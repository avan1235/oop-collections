package pl.edu.mimuw.collections;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Multiset<E> implements IMultiset<E> {
  private final Map<E, Integer> countOfKey;

  public Multiset() {
    this.countOfKey = new HashMap<>();
  }

  public Multiset(Iterable<E> elements) {
    this();
    elements.forEach(this::add);
  }

  @SafeVarargs
  public Multiset(E... elements) {
    this(Arrays.asList(elements));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Iterable<?>)) return false;
    var iterable = (Iterable<?>) o;
    var multiset = new Multiset<>(iterable);
    return countOfKey.equals(multiset.countOfKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(countOfKey);
  }

  @Override
  public String toString() {
    return stream().sorted().map(String::valueOf).collect(Collectors.joining(", ", "{", "}"));
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
    if (occurrences < 0) throw new IllegalArgumentException("Can't add a negative amount.");
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
  public @NotNull Iterator<E> iterator() {
    return countOfKey.entrySet().stream()
        .flatMap(entry -> Collections.nCopies(entry.getValue(), entry.getKey()).stream())
        .iterator();
  }

  @Override
  public Stream<E> stream() {
    return Utility.toStream(this);
  }

  @Override
  public Collection<E> toCollection() {
    var list = new ArrayList<E>();
    iterator().forEachRemaining(list::add);
    return list;
  }
}
