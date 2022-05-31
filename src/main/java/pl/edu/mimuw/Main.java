package pl.edu.mimuw;

import pl.edu.mimuw.collections.MultiSet;

public class Main {

  public static void main(String[] args) {

    MultiSet<Integer> integers = new MultiSet<>();
    integers.add(1);
    integers.add(69, 4);
    integers.add(2137, 2);
    integers.add(0, 3);

    MultiSet<Character> characters = new MultiSet<>();
    characters.add('p', 660);
    characters.add('^', 902);
    characters.add('%', 88);

    System.out.println(integers);
    System.out.println(characters);
  }
}
