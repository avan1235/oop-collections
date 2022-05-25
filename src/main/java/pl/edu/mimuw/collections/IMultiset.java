package pl.edu.mimuw.collections;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

public interface IMultiset<E> extends Iterable<E> {

  int size();

  int count(E element);

  // These semantics copied from the Guava multiset.
  // https://guava.dev/releases/19.0/api/docs/com/google/common/collect/Multiset.html

  /**
   * @return The count of the element before the operation.
   */
  int add(E element, int occurrences);

  /**
   * @return Has this increased the number of distinct values in the set?
   */
  boolean add(E element);

  /**
   * @return The count of the element before the operation.
   */
  int remove(E element, int occurrences);

  /**
   * Remove a <i>single</i> occurrence, if it exists.
   *
   * @return Has the set changed?
   */
  boolean remove(E element);

  Set<E> elementSet();

  boolean contains(E element);

  Iterator<E> iterator();

  Stream<E> stream();

  @Override
  boolean equals(Object object);

  @Override
  int hashCode();

  @Override
  String toString();
}
