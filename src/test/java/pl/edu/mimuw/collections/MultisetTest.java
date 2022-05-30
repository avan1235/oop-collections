package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class MultisetTest {

  @Test
  void testCreatedAsEmpty() {
    final var set = new Multiset<Integer>();

    final var expectedSize = 0;
    final var size = set.size();

    assertEquals(expectedSize, size);
  }

  @Test
  void testAddRemoveIntegers() {
    final var set = new Multiset<Integer>();
    final var element = 10;

    set.add(element, 100);
    set.remove(element, 100);
    set.add(element);
    set.remove(element, 2);
    set.remove(element, 3);

    final var expectedSize = 0;
    final var size = set.size();

    assertEquals(expectedSize, size);
  }

  @Test
  void testEquals() {
    final var set1 = new Multiset<Integer>();
    final var set2 = new Multiset<Integer>();
    final var elements1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    final var elements2 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    for (Integer e : elements1) {
      set1.add(e, 3);
      set2.add(e, 2);
    }

    for (Integer e : elements2)
      set1.remove(e);

    set1.add(1, 100);
    set1.remove(1, 99);
    set2.add(1);

    assertEquals(set1, set2);
  }

  @Test
  void testSetOfSetsEquals() {
    final var bigSet = new Multiset<Multiset<Integer>>();
    final var set1 = new Multiset<Integer>();
    final var set2 = new Multiset<Integer>();
    final var elements1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    final var elements2 = new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1};

    for (Integer e : elements1) {
      set1.add(e, 3);
      set2.add(e, 2);
    }

    for (Integer e : elements2)
      set1.remove(e);

    bigSet.add(set1);
    bigSet.remove(set2);

    assertEquals(0, bigSet.size());
  }

  @Test
  void testExceptions() {
    final var set = new Multiset<Integer>();
    assertThrowsExactly(IllegalArgumentException.class, () -> set.add(1, -1));
    assertThrowsExactly(IllegalArgumentException.class, () -> set.remove(1, -1));
  }
}
