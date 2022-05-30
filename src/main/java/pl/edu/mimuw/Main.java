package pl.edu.mimuw;

import pl.edu.mimuw.collections.Multimap;
import pl.edu.mimuw.collections.Multiset;

public class Main {

  public static void main(String[] args) {
    final var intSet = new Multiset<Integer>();

    intSet.add(1, 5);
    intSet.add(2, 5);
    intSet.add(3, 5);
    intSet.remove(1, 4);
    intSet.remove(2, 3);
    intSet.remove(3, 2);
    System.out.println(intSet);

    final var setSet = new Multiset<Multiset<Integer>>();
    setSet.add(intSet, 5);
    setSet.remove(intSet);
    System.out.println(setSet);

    final var exnSet = new Multiset<Exception>();
    exnSet.add(new NullPointerException("I like NPE the most"), 3);
    exnSet.add(new IndexOutOfBoundsException(), 1);
    System.out.println(exnSet);

    final var bigSet = new Multiset<Multiset<Integer>>();
    bigSet.add(intSet, 420000000);
    System.out.println(bigSet);

    final var fromIterSet = new Multiset<>(intSet);
    System.out.println(fromIterSet);

    final var intMap = new Multimap<Integer, Integer>();
    intMap.put(1,2);
    intMap.put(1,3);
    intMap.put(2,3);
    System.out.println("\nInteger Multimap: " + intMap);
    System.out.println("Keys: " + intMap.keys());
  }
}
