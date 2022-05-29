package pl.edu.mimuw.collections;

import java.util.*;
import java.util.function.Function;

public class ArrayMultiSet<E> implements IMultiset<E> {
  private final List<E> values;
  private final List<Integer> amount;
  private int size;

  public ArrayMultiSet() {
    values = new ArrayList<>();
    amount = new ArrayList<>();
    size = 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public int count(E element) {
    int index = values.indexOf(element);
    if (index == -1) return 0;
    else return amount.get(index);
  }

  @Override
  public int add(E element, int occurrences) {
    if (occurrences <= 0) throw new InvalidOccurrenceNumberException();
    int index = values.indexOf(element);
    if (index == -1) {
      values.add(element);
      amount.add(occurrences);
    } else {
      amount.set(index, amount.get(index) + occurrences);
    }
    size += occurrences;
    return occurrences;
  }

  @Override
  public boolean add(E element) {
    return add(element, 1) == 1;
  }

  @Override
  public int remove(E element, int occurrences) {
    if (occurrences <= 0) throw new InvalidOccurrenceNumberException();
    int index = values.indexOf(element);
    if (index == -1)
      return 0;
    int actAmount = amount.get(index);
    if (actAmount <= occurrences) {
      values.remove(index);
      amount.remove(index);
      size -= actAmount;
      return actAmount;
    }
    amount.set(index, actAmount - occurrences);
    size -= occurrences;
    return occurrences;
  }

  @Override
  public boolean remove(E element) {
    return remove(element, 1) == 1;
  }

  @Override
  public Set<E> elementSet() {
    return Set.copyOf(values);
  }

  @Override
  public boolean contains(E element) {
    return values.contains(element);
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      int occurrence = 0;
      final Iterator<E> iterator = values.iterator();
      E element = iterator.next();

      @Override
      public boolean hasNext() {
        return iterator.hasNext() || amount.get(values.indexOf(element)) < occurrence;
      }

      @Override
      public E next() {
        if (amount.get(values.indexOf(element)) == occurrence) {
          element = iterator.next();
          occurrence = 0;
        }
        occurrence++;
        return element;
      }
    };
  }

  @Override
  public Collection<E> removeAllMatching(Function<E, Boolean> matcher) {
    List<E> result = new ArrayList<>();
    for (int i = 0; i < values.size(); i++)
      if (matcher.apply(values.get(i))) {
        for (int j = 0; j < amount.get(i); j++)
          result.add(values.get(i));
        values.remove(i);
        size -= amount.get(i);
        amount.remove(i);
        i--;
      }
    return result;
  }

  @Override
  public Collection<E> getAllMatching(Function<E, Boolean> matcher) {
    List<E> result = new ArrayList<>();
    for (int i = 0; i < values.size(); i++)
      if (matcher.apply(values.get(i))) {
        for (int j = 0; j < amount.get(i); j++)
          result.add(values.get(i));
      }
    return result;
  }

  @Override
  public <R> IMultiset<R> map(Function<E, R> mapping) {
    IMultiset<R> result = new ArrayMultiSet<>();
    for (int i = 0; i < values.size(); i++)
      result.add(mapping.apply(values.get(i)), amount.get(i));
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ArrayMultiSet<?> that = (ArrayMultiSet<?>) o;
    return values.equals(that.values) && amount.equals(that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values, amount);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("ArrayMultiSet{");
    for (int i = 0; i < values.size(); i++) {
      for (int j = 0; j < amount.get(i); j++) {
        builder.append(values.get(i));
        if (i + 1 < values.size() || j + 1 < amount.get(i))
          builder.append(", ");
      }
    }
    builder.append('}');
    return builder.toString();
  }

  public static class InvalidOccurrenceNumberException extends RuntimeException {
  }
}
