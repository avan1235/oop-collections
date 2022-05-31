package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultisetTest {
  @Test
  void testAdd() {
    Multiset<Integer> intMultiset = new Multiset<>();

    assertTrue(intMultiset.add(1));
    assertTrue(intMultiset.add(2));
    assertTrue(intMultiset.add(3));
    assertEquals(1, intMultiset.getOccurrences(1));
    assertEquals(1, intMultiset.getOccurrences(2));
    assertEquals(1, intMultiset.getOccurrences(3));

    assertEquals(8 , intMultiset.add(2, 7));
    assertEquals(8, intMultiset.getOccurrences(2));

    assertEquals(10, intMultiset.size());
  }

  @Test
  void testRemove() {
    Multiset<Integer> intMultiset = new Multiset<>();

    intMultiset.add(1);
    intMultiset.add(4);
    intMultiset.add(3);
    intMultiset.add(4);
    intMultiset.add(4);
    intMultiset.add(4);
    intMultiset.add(2, 7);
    intMultiset.add(2);

    intMultiset.remove(4, 1);
    assertEquals(3, intMultiset.getOccurrences(4));
    intMultiset.remove(4, 2);
    assertEquals(1, intMultiset.getOccurrences(4));
    intMultiset.remove(2, 1);
    intMultiset.remove(2, 1);
    assertEquals(6, intMultiset.getOccurrences(2));

    assertEquals(9, intMultiset.size());
  }

  @Test
  void testContains() {
    Multiset<Integer> intMultiset = new Multiset<>();

    intMultiset.add(6, 10);
    intMultiset.add(0, 6);
    intMultiset.add(66);

    assertTrue(intMultiset.contains(66));
    assertTrue(intMultiset.contains(6));

    intMultiset.remove(6, 10);
    assertFalse(intMultiset.contains(6));

    assertTrue(intMultiset.contains(0));
  }
}
