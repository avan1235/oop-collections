package pl.edu.mimuw.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.*;

public class Multiset<E> implements IMultiset<E> {

  private Map<E, Integer> elements;
  private int size;

  public Multiset() {
    this.elements = new HashMap<E, Integer>();
    this.size = 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o.getClass() == Multiset.class))
      return false;
    var l1 = new ArrayList<E>();
    var l2 = new ArrayList<E>();
    Iterator<E> it = this.iterator();
    while (it.hasNext()) {
      l1.add(it.next());
    }
    it = ((Multiset<E>) o).iterator();
    while (it.hasNext()) {
      l2.add(it.next());
    }
    return l1.equals(l2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(elements);
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public int count(E element) {
    if (!elements.containsKey(element))
      return 0;
    return elements.get(element);
  }

  //adds elements 'occurrences' times
  // if it is not null and
  @Override
  public int add(E element, int occurrences) {
    if (element == null)
      return 0;

    this.size += occurrences;
    if (!elements.containsKey(element)) {
      elements.put(element, occurrences);
      return occurrences;
    }
    int currentOccurrences = this.count(element);
    elements.remove(element);
    elements.put(element, currentOccurrences + occurrences);
    return currentOccurrences + occurrences;
  }

  //adds one element
  //if it doesn't exist in multiset
  @Override
  public boolean add(E element) {
    if (element == null)
      return false;
    if (elements.containsKey(element))
      return false;
    elements.put(element, 1);
    this.size++;
    return true;
  }

  //removes 'occurrences' copies of elements
  //or all of them if occurrences is bigger
  //than count(element)
  @Override
  public int remove(E element, int occurrences) {
    if (!elements.containsKey(element))
      return 0;
    int currentOccurrences = this.count(element);
    elements.remove(element);
    if (currentOccurrences - occurrences > 0)
      elements.put(element, currentOccurrences - occurrences);
    this.size -= Math.min(occurrences, currentOccurrences);
    return Math.max(0, currentOccurrences - occurrences);
  }

  //removes all copies of element
  //or returns false if it doesn't exist
  @Override
  public boolean remove(E element) {
    if (!elements.containsKey(element))
      return false;
    int currentOccurrences = this.count(element);
    elements.remove(element);
    //wasn't sure how to understand remove
    //so I leave commented second way
    //of removing single element
    //  if (currentOccurrences > 1)
    //    elements.put(element, currentOccurrences - 1);
    this.size -= currentOccurrences;
    return true;
  }

  @Override
  public Set<E> elementSet() {
    return elements.keySet();
  }

  @Override
  public boolean contains(E element) {
    return elements.containsKey(element);
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private int browsedAll = 0;
      private int browsedCurrent = 0;
      private Set<E> setElements = Multiset.this.elements.keySet();
      private Iterator<E> it = setElements.iterator();
      private E currentElement = it.next();

      @Override
      public boolean hasNext() {
        if (browsedAll >= Multiset.this.size)
          return false;
        return true;
      }

      @Override
      public E next() {
        browsedAll++;
        if (browsedCurrent < Multiset.this.elements.get(currentElement)) {
          browsedCurrent++;
          return currentElement;
        }
        browsedCurrent = 1;
        currentElement = it.next();
        return currentElement;
      }
    };

  }

  public String toString() {
    if (this.size == 0)
      return "Empty set";
    var sb = new StringBuilder();
    sb.append("This multiset contains following elements: ");
    Iterator<E> it = this.iterator();
    while (it.hasNext()) {
      sb.append(it.next()).append(" ");
    }
    return sb.toString();
  }
}
