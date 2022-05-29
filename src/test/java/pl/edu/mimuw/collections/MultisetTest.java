package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MultisetTest {
  @Test
  void testMultiSetSize() {
    IMultiset<Character> set = new ArrayMultiSet<>();
    assertEquals(0, set.size());
    set.add('x', 5);
    assertEquals(5, set.size());
    set.add('a');
    assertEquals(6, set.size());
    set.add('a', 4);
    assertEquals(10, set.size());
    set.remove('x', 3);
    assertEquals(7, set.size());
    set.remove('x', 10);
    assertEquals(5, set.size());
  }

  @Test
  void testMultiSetContains() {
    IMultiset<Character> set = new ArrayMultiSet<>();
    assertFalse(set.contains('a'));
    set.add('x', 5);
    assertTrue(set.contains('x'));
    set.add('a');
    assertTrue(set.contains('a'));
    set.add('a', 3);
    assertTrue(set.contains('a'));
    set.remove('x', 5);
    assertFalse(set.contains('x'));
    assertFalse(set.contains('b'));
  }

  @Test
  void testMultiSetElementsSet() {
    IMultiset<Character> set = new ArrayMultiSet<>();
    set.add('x', 5);
    set.add('a');
    Set<Character> elementSet = set.elementSet();
    assertTrue(elementSet.contains('a'));
    assertTrue(elementSet.contains('x'));
    assertFalse(elementSet.contains('b'));
  }
}
