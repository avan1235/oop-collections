package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultimapTest {
  @Test
  void testAdd() {
    final var map = new Multimap<Integer, Integer>();
    map.put(1,1);
    map.put(1,2);
    map.put(2,1);
    map.put(2,2);
    map.put(1,1);

    for (int i : new Integer[]{1, 2}) {
      assertTrue(map.get(i).contains(1));
      assertTrue(map.get(i).contains(2));
      assertEquals(2, map.get(i).size());
    }
  }

  @Test
  void testAddAllRemoveOne() {
    final var map = new Multimap<Integer, Integer>();
    final var numbers = new Integer[]{1, 2, 3, 4, 5, 6};

    map.putAll(1, List.of(numbers));
    map.remove(1,3);

    for (int i : numbers)
      if (i != 3) assertTrue(map.containsValue(i));
      else assertFalse(map.containsValue(i));
  }

  @Test
  void testSameElementNotTwice() {
    final var map = new Multimap<Integer, Object>();
    final var obj = new Object();

    for (int i : new Integer[]{1, 2}) {
      assertTrue(map.put(i, obj));
      assertFalse(map.put(i, obj));
    }
  }

  @Test
  void testMapsEquality() {
    final var map1 = new Multimap<Integer, String>();
    final var map2 = new Multimap<Integer, String>();

    map1.putAll(0, List.of(new String[]{"siała", "baba", "mak"}));
    map2.putAll(1, List.of(new String[]{"siała", "mak", "baba"}));

    map1.putAll(1, map1.removeAll(0));
    assertEquals(map1, map2);
  }
}
