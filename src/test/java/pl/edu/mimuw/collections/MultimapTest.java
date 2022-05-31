package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MultimapTest {

  @Test
  void testAddSmallStrings() {
    var m = new Multimap<Integer, String>();
    assertTrue(m.put(1, "a"));
    assertTrue(m.put(2, "b"));
    assertTrue(m.put(3, "c"));
    assertTrue(m.put(4, "a"));
    assertTrue(m.put(1, "b"));
    assertEquals(5, m.size());

    assertTrue(m.containsValue("a"));
    assertFalse(m.containsValue("e"));
    assertTrue(m.containsValue("c"));
    assertTrue(m.containsKey(4));
    assertFalse(m.containsKey(14));
  }
  @Test
  void testRemove() {
    var m = new Multimap<Integer, String>();
    assertTrue(m.put(1, "a"));
    assertTrue(m.put(2, "b"));
    assertTrue(m.put(3, "c"));
    assertTrue(m.put(4, "a"));
    assertTrue(m.put(1, "b"));
    assertEquals(5, m.size());

    assertTrue(m.containsValue("a"));
    assertFalse(m.containsValue("e"));
    assertTrue(m.containsValue("c"));
    assertTrue(m.containsKey(4));
    assertFalse(m.containsKey(14));
    assertTrue(m.remove(3, "c"));
    assertFalse(m.containsValue("c"));
    assertFalse(m.remove(5, "11"));
  }
  @Test
  void testContains() {
    var m = new Multimap<Integer, String>();
    assertTrue(m.put(1, "a"));
    assertTrue(m.put(2, "x"));
    assertTrue(m.put(3, "c"));
    assertTrue(m.put(4, "a"));
    assertTrue(m.put(1, "f"));
    assertEquals(5, m.size());

   assertTrue(m.containsValue("a"));
    assertFalse(m.containsValue("e"));
    assertTrue(m.containsValue("c"));
    assertTrue(m.containsKey(4));
    assertFalse(m.containsKey(14));
    assertTrue(m.remove(3, "c"));
    assertFalse(m.containsValue("c"));
    assertFalse(m.remove(5, "11"));

    assertTrue(m.put(1, "b"));
    assertEquals(6, m.size());
    assertTrue(m.containsValue("b"));
    assertTrue(m.remove(1, "b"));
     assertFalse(m.containsValue("b"));
  }
}
