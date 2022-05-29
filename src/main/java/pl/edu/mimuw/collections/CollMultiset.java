package pl.edu.mimuw.collections;

import java.util.*;

import static java.lang.Integer.max;

public class CollMultiset<E> implements IMultiset<E> {

  private final HashMap<E, Integer> map;

  public CollMultiset() {
    map = new HashMap<E, Integer>();
  }

  @Override
  public int size() {
    int sum = 0;
    for(E key : map.keySet()) {
      sum += map.get(key);
    }
    return sum;
  }

  @Override
  public int count(E element) {
    Integer count = map.get(element);
    if (count == null)
      return 0;
    else
      return count;
  }


  public int add(E element, int occurrences) {
    if (occurrences <= 0)
      throw new RuntimeException();
    map.put(element, occurrences + count(element));
    return count(element);
  }

  public boolean add(E element) {
    return add(element, 1) == 1;
  }

  @Override
  public int remove(E element, int occurrences) {
    int newOcc = max(0, count(element) - occurrences);
    if (newOcc > 0)
      map.put(element, newOcc);
    else
      map.remove(element);
    return count(element);
  }

  @Override
  public boolean remove(E element) {
    return count(element) != 0
      && remove(element, 1) == 0;
  }

  @Override
  public Set<E> elementSet() {
    return map.keySet();
  }

  @Override
  public boolean contains(E element) {
    return map.containsKey(element);
  }

  @Override
  public Iterator<E> iterator(){
    return new Iterator<E>() {
      private final Map<E, Integer> m = CollMultiset.this.map;
      private final Iterator<E> mIter = m.keySet().iterator();
      private int cnt = 0;
      private E curr = null;

      @Override
      public boolean hasNext() {
        return cnt != 0 || mIter.hasNext();
      }

      @Override
      public E next() {
        assert (hasNext());
        if (cnt > 0) {
          cnt--;
        } else {
          curr = mIter.next();
          cnt = count(curr) - 1;
        }
        return curr;
      }

    };
  }

}
