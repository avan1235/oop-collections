package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultimapTest {

  @Test
  void addingTest() {
    MultiMap<Integer, Integer> map = new MultiMap<>();
    map.put(1, 1);
    map.put(1, 11);
    map.put(3, 333);
    map.put(1, 1111);
    map.put(5, 55);
    map.put(1, 11);
    map.put(5, 55);
    map.put(2137, 42069);
    assertEquals(6, map.size());
    assertEquals(3, map.get(1).size());
  }

  @Test
  void removingTest() {
    MultiMap<Integer, Integer> map = new MultiMap<>();
    map.put(1, 1);
    map.put(1, 11);
    map.put(3, 333);
    map.put(1, 1111);
    map.put(5, 55);
    map.put(5, 5555);
    map.put(5, 55555);
    map.remove(1, 11);
    map.put(1, 11);
    map.put(5, 55);
    map.put(2137, 42069);
    map.remove(5, 55);
    map.remove(5, 55555);
    assertEquals(3, map.removeAll(1).size());
    assertEquals("[5555]", map.get(5).toString());
  }

  @Test
  void containsTest() {
    MultiMap<Integer, Integer> map = new MultiMap<>();
    map.put(1, 1);
    map.put(1, 11);
    map.put(3, 333);
    map.put(1, 1111);
    map.put(5, 55);
    map.put(5, 5555);
    map.put(5, 55555);
    map.remove(1, 11);
    map.put(1, 11);
    map.put(5, 55);
    map.put(2137, 42069);
    map.remove(5, 55);
    map.remove(5, 55555);
    assert(map.containsKey(5));
    assert(map.containsValue(42069));
    map.remove(5,5555);
    assert(!map.containsKey(5));
  }





}
