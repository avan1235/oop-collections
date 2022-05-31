package pl.edu.mimuw.collections;

import java.util.*;

public class Multiset<T> implements IMultiset<T>, Iterable<T>{
  private int size;
  private final Map<T, Integer> map;

  public Multiset() {
    this.size = 0;
    map = new HashMap<>();
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public int count(T element) {
    return map.getOrDefault(element, 0);
  }

  @Override
  public int add(T element, int occurrences) {
    int occurrencesToAdd = count(element) + occurrences;
    if (occurrencesToAdd != 0) {
      map.put(element, count(element) + occurrences);
    }
    size += occurrences;
    return count(element);
  }

  @Override
  public boolean add(T element) {
    map.put(element, count(element) + 1);
    size++;
    return true;
  }

  @Override
  public int remove(T element, int occurrences) {
    int numOfOccurences = count(element);

    map.remove(element);
    size -= occurrences - numOfOccurences;

    if (numOfOccurences != 0) {
      numOfOccurences -= occurrences;
      numOfOccurences = Math.max(numOfOccurences, 0);
      add(element, numOfOccurences);
      return numOfOccurences;
    }
    else {
      return 0;
    }
  }

  @Override
  public boolean remove(T element) {
    if (count(element) == 0) {
      return false;
    }
    remove(element, 1);
    return true;
  }

  @Override
  public Set<T> elementSet() {
    return map.keySet();
  }

  @Override
  public boolean contains(T element) {
    return count(element) != 0;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {
      final Iterator<T> keySetIt = map.keySet().iterator();
      int occurrencesLeft = 0;
      T currentKey = null;

      @Override
      public boolean hasNext() {
        if (occurrencesLeft > 0) {
          return true;
        }
        else {
          return keySetIt.hasNext();
        }
      }

      @Override
      public T next() {
        if (occurrencesLeft == 0) {
          currentKey = keySetIt.next();
          occurrencesLeft = Multiset.this.count(currentKey);
        }

        occurrencesLeft--;

        return currentKey;
      }
    };
  }
}
