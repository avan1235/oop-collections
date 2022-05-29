package pl.edu.mimuw;

import pl.edu.mimuw.collections.ArrayMultiMap;
import pl.edu.mimuw.collections.ArrayMultiSet;

public class Main {

  public static void main(String[] args) {
    ArrayMultiSet<Integer> someSet = new ArrayMultiSet<>();
    someSet.add(1);
    someSet.add(2, 2);
    someSet.add(3, 3);
    someSet.add(10, 10);
    System.out.println(someSet);

    ArrayMultiMap<Integer, Character> someMap = new ArrayMultiMap<>();
    someMap.put(1, 'a');
    someMap.put(1, 'b');
    someMap.put(1, 'b');
    someMap.put(2, 'a');
    someMap.put(3, 'a');
    someMap.put(4, 'd');
    someMap.put(4, 'd');
    System.out.println(someMap);
  }
}
