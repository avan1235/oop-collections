package pl.edu.mimuw.collections;

import java.util.*;

public class MultiSet<E> implements IMultiset<E> {
  private int sumOcc;
  private final Map<E, Integer> map;

  public MultiSet() {
    this.sumOcc = 0;
    this.map = new HashMap<>();
  }

  @Override
  public int size() {
    return sumOcc;
  }

  @Override
  public int count(E element) {
    if (element == null)
      return 0;
    if (this.map.containsKey(element))
      return this.map.get(element);
    return 0;
  }

  @Override
  public int add(E element, int occurrences) {
    assert(occurrences >= 0);
    if (occurrences == 0)
      return this.count(element);
    else {
      if (element == null)
        return 0;
      this.map.put(element, this.count(element) + occurrences);
      this.sumOcc += occurrences;
      return this.map.get(element);
    }
  }

  @Override
  public boolean add(E element) {
    if (element == null) {
      return false;
    }
    this.map.put(element, this.count(element) + 1);
    this.sumOcc++;
    return (this.count(element) != 1);
  }

  @Override
  public int remove(E element, int occurrences) {
    assert (occurrences >= 0);
    if (element == null)
      return 0;
    if (this.map.containsKey(element)) {
      if (this.map.get(element) <= occurrences) {
        this.sumOcc-=this.map.get(element);
        this.map.remove(element);
        return 0;
      }
      this.map.put(element, this.map.get(element) - occurrences);
      this.sumOcc-=occurrences;
      return this.map.get(element);
    }
    return 0;
  }

  @Override
  public boolean remove(E element) {
    if (element == null)
      return false;
    if (this.map.containsKey(element)) {
      if (this.map.get(element) > 1) {
        this.map.put(element, this.map.get(element) - 1);
      } else {
        this.map.remove(element);
      }
      this.sumOcc--;
      return true;
    }
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (this.getClass() != obj.getClass())
      return false;
    MultiSet<E> otherSet = (MultiSet<E>) obj;
    if (this.sumOcc != otherSet.sumOcc)
      return false;
    for (E element : otherSet.elementSet()){
      if (this.count(element) != otherSet.count(element))
        return false;
    }
    return true;
  }


  @Override
  public Set<E> elementSet() {
    return this.map.keySet();
  }

  @Override
  public boolean contains(E element) {
    return this.map.containsKey(element);
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      private final Map<E, Integer> map = MultiSet.this.map;
      private final Iterator<E> mapIter = map.keySet().iterator();
      private E currentElement = null;
      private int currentElementCountdown = 0;


      @Override
      public boolean hasNext() {
        if (mapIter.hasNext())
          return true;
        return this.currentElementCountdown > 1;
      }

      @Override
      public E next() {
        assert(hasNext());
        if (currentElementCountdown > 1)
          currentElementCountdown--;
        else {
          currentElement = mapIter.next();
          currentElementCountdown = count(currentElement);
        }
        return currentElement;
      }
    };
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder("{");
    for (E element : this.elementSet()) {
      res.append(element.toString() + ", ");
      int amountOfThisElement = this.count(element);
      for (int i = 1; i < amountOfThisElement; i++) {
        res.append(element + ", ");
      }
    }
    res.replace(res.length()-2, res.length(), "}");
    return res.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.map, this.sumOcc);
  }


}
