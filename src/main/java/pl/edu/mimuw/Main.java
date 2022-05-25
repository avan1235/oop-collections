package pl.edu.mimuw;

import pl.edu.mimuw.collections.IMultimap;
import pl.edu.mimuw.collections.Multiset;
import pl.edu.mimuw.collections.Multimap;

public class Main {

  public static void main(String[] args) {
    Multiset S = new Multiset<Integer>();
    S.add(5, 4);
    S.add(6, 5);
    S.remove(4, 1);
    S.remove(6, 2);
    S.add(4, 2);
    System.out.println(S.toString());
    System.out.println();
    var M = new Multimap<Integer, String>();
    M.put(5, "abc");
    M.put(6, "def");
    M.put(5, "xyz");
    M.put(6, "pol");
    M.put(7, "utf");
    M.remove(6, "pol");
    System.out.println(M.toString());
  }
}
