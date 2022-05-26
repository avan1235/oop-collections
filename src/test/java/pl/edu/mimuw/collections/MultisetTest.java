package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class MultisetTest {
  @Test
  void testAdd() {
    var s = new Multiset<>();
    assertTrue(s.add(69));
    assertFalse(s.add(69));
    assertFalse(s.add(69));
    assertEquals(3, s.add(69, 4));
    assertEquals(7, s.count(69));
    assertEquals(7, s.size());

    assertEquals(0, s.add(2137, 5));
    assertEquals(5, s.add(2137, 2));
    assertEquals(7, s.add(2137, 0));
    assertEquals(7, s.add(2137, 0));
    assertEquals(7, s.count(2137));
    assertEquals(7 + 7, s.size());
  }

  @Test
  void testRemove() {
    var s = new Multiset<>();
    s.add(69, 10);
    assertEquals(10, s.remove(69, 5));
    assertEquals(5, s.count(69));
    assertTrue(s.remove(69));
    assertTrue(s.remove(69));
    assertTrue(s.remove(69));
    assertTrue(s.remove(69));
    assertTrue(s.remove(69));
    assertEquals(0, s.count(69));
    assertFalse(s.remove(69));
    assertEquals(0, s.count(69));
  }

  @Test
  void testViews() {
    var s = new Multiset<Integer>();
    s.add(69, 5);
    s.add(2137, 3);
    s.add(42, 0);
    assertEquals(new TreeSet<>(Arrays.asList(69, 2137)), s.elementSet());

    var elements = new ArrayList<>();
    for (var x : s) elements.add(x);
    assertEquals(Arrays.asList(69, 69, 69, 69, 69, 2137, 2137, 2137), elements);
  }

  @Test
  void testEquals() {
    var a = new Multiset<>(1, 2, 3, 3, 3);
    var b = new Multiset<>(3, 2, 1, 3, 3);
    var c = new Multiset<>(1, 1, 1, 1, 3);
    assertEquals(a, b);
    assertEquals(b, a);
    assertNotEquals(a, c);
    assertNotEquals(b, c);
    assertEquals(a.hashCode(), b.hashCode());
    assertEquals(b.hashCode(), a.hashCode());

    assertNotEquals(new Multiset<>(1, 1, 2), new Multiset<>(1, 2, 2));

    // Not guaranteed but most probably.
    assertNotEquals(a.hashCode(), c.hashCode());
    assertNotEquals(b.hashCode(), c.hashCode());
  }

  @Test
  void testToString() {
    var s = new Multiset<>(1, 2, 3, 3, 3, 4);
    assertEquals("{1, 2, 3, 3, 3, 4}", s.toString());
  }
}
