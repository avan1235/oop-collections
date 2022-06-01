package pl.edu.mimuw;

import pl.edu.mimuw.collections.Multiset;

public class Main {

  public static void main(String[] args) {
    var multiSet = new Multiset<String>();
    multiSet.add("Hello");
    multiSet.add("Hello", 4);
    multiSet.remove("Hello", 2);
    System.out.println(multiSet);
    multiSet.add("World");
    multiSet.add("World");
    System.out.println(multiSet);
  }
}
