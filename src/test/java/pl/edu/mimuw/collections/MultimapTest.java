package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultimapTest {
  @Test
  void testMultiMapSize() {
    IMultimap<Integer, Character> map = new ArrayMultiMap<>();
    assertEquals(0, map.size());
    map.put(5, 'x');
    assertEquals(1, map.size());
    map.put(1, 'x');
    assertEquals(2, map.size());
    map.put(5, 'a');
    assertEquals(3, map.size());
    map.put(5, 'x');
    assertEquals(4, map.size());
    map.remove(5, 'x');
    assertEquals(3, map.size());
    map.removeAll(5);
    assertEquals(1, map.size());
  }

  @Test
  void testMultiMapContains() {
    IMultimap<Integer, Character> map = new ArrayMultiMap<>();
    assertFalse(map.containsKey(1));
    assertFalse(map.containsValue('a'));
    map.put(1, 'a');
    assertTrue(map.containsKey(1));
    assertTrue(map.containsValue('a'));
    map.remove(1, 'a');
    assertFalse(map.containsKey(1));
    assertFalse(map.containsValue('a'));
  }

  @Test
  void testMultiMapKeys() {
    IMultimap<Integer, Character> map = new ArrayMultiMap<>();
    map.put(5, 'x');
    map.put(1, 'a');
    IMultiset<Integer> keys = map.keys();
    assertTrue(keys.contains(5));
    assertTrue(keys.contains(1));
    assertFalse(keys.contains(0));
  }
}
