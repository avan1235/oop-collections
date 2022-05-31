package pl.edu.mimuw.collections;

import java.util.*;

public class Multiset<E> implements IMultiset<E> {

  private final Map<E, Integer> set;

  public Multiset() {
    this.set = new HashMap<>();
  }

  @Override
  public int size() {
    int numberOfElements = 0;
    for (int i : this.set.values()) {
      numberOfElements += i;
    }

    return numberOfElements;
  }

  @Override
  public int count(E element) {
    return this.set.getOrDefault(element, 0);
  }

  @Override
  public int add(E element, int occurrences) {
    if (occurrences < 0) throw new IllegalArgumentException();
    int actualNumber = this.count(element);
    if (occurrences == 0) return actualNumber;
    this.set.put( element, actualNumber + occurrences);

    return actualNumber + occurrences;
  }

  @Override
  public boolean add(E element) {
    this.add(element, 1);
    return true;
  }

  @Override
  public int remove(E element, int occurrences) {
    if (occurrences < 0) throw new IllegalArgumentException();
    int actualNumber = this.count(element);
    if (occurrences == 0) return actualNumber;
    if (actualNumber - occurrences > 0) {
      this.set.put(element, actualNumber - occurrences);
      return actualNumber - occurrences;
    } else {
      this.set.remove(element);
      return 0;
    }
  }

  @Override
  public boolean remove(E element) {
    this.remove(element, 1);
    return true;
  }

  @Override
  public Set<E> elementSet() {
    return this.set.keySet();
  }

  @Override
  public boolean contains(E element) {
    return this.count(element) > 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      private final Iterator<E> iter = elementSet().iterator();
      private int ocur = 0;
      private E element = null;

      @Override
      public boolean hasNext() {
        return iter.hasNext() || ocur != 0;
      }

      @Override
      public E next() {
        if(ocur > 0){
          ocur --;
        } else{
          element = iter.next();
          ocur = count(element) - 1;
        }
        return element;
      }
    };
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    Multiset<?> multi = (Multiset<?>) object;
    return this.set.equals(multi) && this.size() == multi.size();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.set);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("MultiSet:\n");
    var helper = this.set.keySet().iterator();

    result.append("[ ");

    helper.forEachRemaining(e -> {
        for (int i=0;i<this.count(e);i++){
          result.append(e)
            .append(", ");
        }
    });

    result.append("]");
    return result.toString();
  }
}
