package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultimapTest {
  @Test
  void testAdd() {
    Multimap<Integer, String> multimap = new Multimap<>();

    assertTrue(multimap.put(1, "jeden"));
    assertFalse(multimap.put(1, "jeden"));
    assertEquals(2, multimap.size());

    assertTrue(multimap.put(1, "one"));
    assertEquals(3, multimap.size());

    assertTrue(multimap.put(2, "dwa"));
    assertTrue(multimap.put(3, "trzy"));
    assertFalse(multimap.put(3, "trzy"));

    assertEquals(6, multimap.size());
  }

  @Test
  void testRemove() {
    Multimap<Integer, String> multimap = new Multimap<>();

    multimap.put(1, "a");
    multimap.put(1, "A");
    multimap.put(2, "B");
    multimap.put(2, "B");
    multimap.put(2, "b");
    multimap.put(2, "b");
    multimap.put(2, "b");
    multimap.put(2, "b");

    assertEquals(8, multimap.size());

    assertTrue(multimap.remove(1, "a"));
    assertTrue(multimap.containsKey(1));
    assertFalse(multimap.containsValue("a"));
    assertEquals(7, multimap.size());

    assertFalse(multimap.remove(1, "a"));

    assertTrue(multimap.remove(2, "b"));
    assertEquals(3, multimap.size());
  }

  @Test
  void testContains() {
    Multimap<Integer, String> multimap = new Multimap<>();

    multimap.put(1, "a");
    multimap.put(1, "A");
    multimap.put(1, "1");
    multimap.put(2, "B");
    multimap.put(2, "B");
    multimap.put(2, "b");
    multimap.put(2, "b");
    multimap.put(2, "b");
    multimap.put(2, "b");
    multimap.put(2, "3");


    assertTrue(multimap.containsValue("a"));

    multimap.remove(1, "a");
    assertFalse(multimap.containsValue("a"));
    assertTrue(multimap.containsKey(1));

    assertFalse(multimap.containsKey(3));
    assertTrue(multimap.containsValue("3"));
    assertTrue(multimap.remove(2, "3"));
    assertFalse(multimap.remove(3, "a"));

    assertTrue(multimap.remove(2, "b"));
    assertEquals(4, multimap.size());
  }
}
