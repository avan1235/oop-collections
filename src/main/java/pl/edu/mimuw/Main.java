package pl.edu.mimuw;

import pl.edu.mimuw.collections.MultiMap;
import pl.edu.mimuw.collections.Multiset;

public class Main {

  public static void main(String[] args) {
    var set = new Multiset<Integer>();
    set.add(6, 2);
    set.add(4, 7);
    set.add(1);
    set.add(14, 2);

    System.out.println(set.size());
    System.out.println(set);

    set.remove(4, 5);

    System.out.println(set.size());
    System.out.println(set);


    var map = new MultiMap<Integer, Integer>();

    map.put(1, 3);
    map.put(1, 5);
    map.put(2, 6);
    for(int i=1;i< 11;i++){
      map.put(1,2);
    }
    map.put(3,7);
    map.put(3,8);

    System.out.println(map);

    map.removeAll(3);

    System.out.println(map);


  }
}
