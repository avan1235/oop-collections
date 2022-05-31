package pl.edu.mimuw;

import pl.edu.mimuw.collections.*;

public class Main {

  public static void main(String[] args) {
    Multimap<Integer, String> multimap = new Multimap<>();
    Multiset<Integer> multiset = new Multiset<>();

    multiset.add(1);
    multiset.add(4);
    multiset.add(3);
    multiset.add(4);
    multiset.add(4);
    multiset.add(4);
    multiset.add(2, 7);
    multiset.add(2);

    multimap.put(1, "a");
    multimap.put(1, "A");
    multimap.put(1, "1");
    multimap.put(2, "B");
    multimap.put(2, "B");
    multimap.put(2, "b");
    multimap.put(2, "b");
    multimap.put(2, "b");
    multimap.put(2, "b");
    multimap.put(2, "3");

    System.out.println(multimap);
    System.out.println(multiset);
  }
}
