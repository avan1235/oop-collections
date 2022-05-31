package pl.edu.mimuw;

import pl.edu.mimuw.collections.Multiset;

public class Main {

  public static void main(String[] args) {
    var multiset = new Multiset<Integer>();

    multiset.add(1);
    multiset.add(1);
    multiset.add(6);
    multiset.add(1);
    multiset.add(7);

    System.out.println(multiset);
  }
}
