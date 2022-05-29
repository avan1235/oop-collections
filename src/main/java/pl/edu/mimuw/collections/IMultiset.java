package pl.edu.mimuw.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

public interface IMultiset<E> {

  int size();

  int count(E element);

  int add(E element, int occurrences);

  boolean add(E element);

  int remove(E element, int occurrences);

  boolean remove(E element);

  Collection<E> removeAllMatching(Function<E, Boolean> matcher);

  Collection<E> getAllMatching(Function<E, Boolean> matcher);

  <R> IMultiset<R> map(Function<E, R> mapping);

  Set<E> elementSet();

  boolean contains(E element);

  Iterator<E> iterator();

  @Override
  boolean equals(Object object);

  @Override
  int hashCode();

  @Override
  String toString();
}

