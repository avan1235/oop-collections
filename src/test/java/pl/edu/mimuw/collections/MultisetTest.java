package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultisetTest {

  @Test
  void basicTest() {
    final IMultiset<String> multiset= new CollMultiset<String>();
    assertEquals(0, multiset.size());
    assertEquals(0, multiset.count("a"));
    assertTrue(multiset.add("a"));
    assertTrue(multiset.remove("a"));
    assertEquals(0, multiset.count("a"));
    assertEquals(2, multiset.add("a", 2));
    assertEquals(2, multiset.count("a"));
  }

  @Test
  void testIterate(){
    final IMultiset<String> multiset = new CollMultiset<String>();
    final TreeMap<String, Integer> map = new TreeMap<String, Integer>();
    multiset.add("a");
    multiset.add("b", 2);
    multiset.add("c", 3);

    assertEquals(6, multiset.size());

    for (Iterator<String> it = multiset.iterator(); it.hasNext(); ) {
      String s = it.next();
      map.put(s, map.getOrDefault(s, 0) + 1);
    }
    assertEquals(3, map.size());
    assertEquals(1, map.get("a"));
    assertEquals(2, map.get("b"));
    assertEquals(3, map.get("c"));

    assertEquals(map.keySet(), multiset.elementSet());
  }
}
