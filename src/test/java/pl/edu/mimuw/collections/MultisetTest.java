package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MultisetTest {
  @Test
  void testRemoveNonExistingValues(){
    Multiset S = new Multiset();
    assertEquals(S.add(1, 1), 1);
    assertEquals(S.add(2, 2), 2);
    assertEquals(S.add(3, 3), 3);
    assertEquals(S.remove(4), false);
    assertEquals(S.remove(5), false);
    assertEquals(S.remove(3, 3), 0);
    assertEquals(S.remove(3), false);
  }

  @Test
  void testRemoveSimpleValues(){
    Multiset S = new Multiset();
    assertEquals(S.add(1, 1), 1);
    assertEquals(S.add(2, 2), 2);
    assertEquals(S.add(3, 3), 3);
    assertEquals(S.contains(2), true);
    assertEquals(S.remove(2), true);
    assertEquals(S.remove(2), false);
    assertEquals(S.remove(3, 2), 1);
    assertEquals(S.contains(3), true);
    assertEquals(S.remove(3, 2), 0);
    assertEquals(S.contains(3), false);
    assertEquals(S.remove(3), false);
  }
  @Test
  void testAddSimpleValues(){
    Multiset S = new Multiset();
    assertEquals(S.add(1, 1), 1);
    assertEquals(S.add(2, 2), 2);
    assertEquals(S.add(3, 3), 3);
    assertEquals(S.add(2, 2), 4);
    assertEquals(S.contains(2), true);
    assertEquals(S.count(2), 4);
    assertEquals(S.count(3), 3);
    assertEquals(S.add(2, 2), 6);
    assertEquals(S.count(2), 6);
  }
}
