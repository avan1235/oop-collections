package pl.edu.mimuw.collections;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class MultiSet<E> implements IMultiset<E> {

  private Map<E, Integer> elements;

  public MultiSet<>() {
    this.elements = new HashMap<>();
  }

  public int size() {
    int result = 0;
    for(var v: this.elements.values()) {
      result += v;
    }
    return result;
  }

  public int count(E element) {
    if(this.elements.get(element) == null) return 0;
    return this.elements.get(element);
  }

  public int add(E element, int occurrences) {
    if(this.elements.get(element) == null) {
      this.elements.put(element, 0);
    }
    int tmp = this.elements.get(element);
    this.elements.put(element, tmp);
    return true;
  }

  public boolean add(E element) {
    if(this.elements.get(element) == null) {
      this.elements.put(element, 0);
    }
    this.elements.get(element)++;
    return true;
  }

  public int remove(E element, int occurrences) {
    this.elements.get(element) -= occurrences;
  }


}
