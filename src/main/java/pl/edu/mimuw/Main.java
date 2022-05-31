package pl.edu.mimuw;

import pl.edu.mimuw.collections.*;

public class Main {

  public static void main(String[] args) {
    var set = new MultiSet<>();
    set.add("student");
    set.add("piwo");
    set.add(".");
    set.add(".");
    set.add("*");
    System.out.println(set);
    var map = new MultiMap<>();
    map.put(".", ".");
    map.put(".", "*");
    map.put("student", "piwo");
    System.out.println(map);
  }
}
