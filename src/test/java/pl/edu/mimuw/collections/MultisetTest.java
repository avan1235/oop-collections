package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MultisetTest {

  @Test
  void addTest() {
    var multiSet = new Multiset<Integer>();
    multiSet.add(1, 5);
    multiSet.add(1);
    multiSet.add(7,7);

    assertEquals(multiSet.count(1), 6);
    assertEquals(multiSet.count(7), 7);
    assertEquals(multiSet.count(0), 0);
  }

  @Test
  void removeTest() {
    var multiSet = new Multiset<Integer>();
    multiSet.add(1, 5);
    multiSet.add(1);
    multiSet.add(7,7);

    assertTrue(multiSet.remove(1));
    assertEquals(1, multiSet.remove(1, 4));
    assertEquals(0, multiSet.remove(7, 7));
    assertEquals(0, multiSet.remove(0, 3));

    assertEquals(1, multiSet.count(1));
    assertEquals(0, multiSet.count(7));
    assertEquals(0, multiSet.count(0));
  }

  @Test
  void iteratorTest() {
    var multiSet = new Multiset<Integer>();
    multiSet.add(1, 5);
    multiSet.add(1);
    multiSet.add(7,7);

    ArrayList<Integer> list = new ArrayList<>();
    var expectedList = new ArrayList<Integer>();

    for (int j = 6; j < 8; j++) {
      for (int i = 0; i < j; i++) {
        expectedList.add(j == 6 ? 1 : 7);
      }
    }

    for (var elem: multiSet) {
      list.add(elem);
    }

    assertArrayEquals(expectedList.toArray(), list.toArray());
  }
}
