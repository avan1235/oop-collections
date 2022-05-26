package pl.edu.mimuw;

import pl.edu.mimuw.collections.Multimap;
import pl.edu.mimuw.collections.Multiset;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    var set = new Multiset<Integer>();
    for (var x : List.of(69, 2137, 69, 1, 2, 3)) set.add(x);
    System.out.println(set);

    var map = new Multimap<Integer, String>();

    // I don't know, made more sense in my head.
    var s = "qwertyuiopasdfghjklzxcvbnm";
    for (var x : List.of(1, 2, 3, 42, 6969))
      for (var y = 0; y < x % 5 + 1; ++y)
        map.put(x, String.valueOf(s.charAt((x + y) % s.length())));

    System.out.println(map);
  }
}
