package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MultimapTest {
  @Test
  void testAdd() {
    var s = new Multimap<Integer, String>();
    assertTrue(s.put(69, "a"));
    assertTrue(s.put(69, "b"));
    assertTrue(s.put(69, "b"));
    assertTrue(s.put(69, "d"));
    assertTrue(s.put(2137, "x"));
    assertEquals(5, s.size());

    assertTrue(s.containsValue("a"));
    assertTrue(s.containsValue("b"));
    assertTrue(s.containsValue("d"));
    assertFalse(s.containsValue("c"));

    assertEquals(new Multiset<>(List.of("a", "b", "b", "d")), s.get(69));
    assertEquals(List.of("x"), s.get(2137));
  }

  @Test
  void testRemove() {
    var s = new Multimap<Integer, String>();
    s.put(69, "a");
    s.put(69, "b");
    s.put(69, "b");
    s.put(69, "b");
    assertEquals(4, s.size());
    assertFalse(s.remove(68, "b"));
    assertFalse(s.remove(69, "d"));
    assertEquals(4, s.size());
    assertTrue(s.remove(69, "b"));
    assertEquals(3, s.size());
    assertEquals(new Multiset<>("a", "b", "b"), s.removeAll(69));
    assertEquals(0, s.size());

    var z = new Multimap<Integer, Integer>();
    z.put(1, 1);
    z.remove(1, 1);
    assertFalse(z.containsKey(1));
    assertFalse(z.containsValue(1));
  }

  @Test
  void testViews() {
    var e = List.of(Map.entry(69, "a"), Map.entry(69, "a"), Map.entry(2137, "xd"));
    var s = new Multimap<>(e);

    assertEquals(new HashSet<>(List.of(69, 2137)), s.keySet());
    assertEquals(new Multiset<>(List.of(69, 69, 2137)), s.keys());

    var entries = new ArrayList<Map.Entry<Integer, String>>();
    for (var x : s) entries.add(x);
    assertEquals(new Multimap<>(entries), s);
  }

  @Test
  void testEquals() {
    var a = new Multimap<>();
    a.put("fact", "interpretation");
    a.put("everything", "water");
    var b = new Multimap<>();
    var c = new Multimap<>();
    a.iterator().forEachRemaining(entry -> b.put(entry.getKey(), entry.getValue()));
    a.iterator().forEachRemaining(entry -> c.put(entry.getKey(), entry.getValue()));
    c.removeAll("everything");
    assertEquals(a, b);
    assertNotEquals(a, c);
    assertNotEquals(b, c);
    assertEquals(c, c);
  }
}
