package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultisetTest {


  @Test
  void addingTest() {
    MultiSet<Integer> set = new MultiSet<>();
    set.add(1);
    set.add(2,2);
    set.add(3,3);
    set.remove(4);
    set.remove(5,5);
    set.add(6);
    set.add(2137, 0);
    assertEquals(7, set.size());
  }

  @Test
  void removingTest() {
    MultiSet<Integer> set = new MultiSet<>();
    set.add(1);
    set.add(2,2);
    set.add(3,3);
    set.add(4, 4);
    set.add(5,5);
    set.remove(2137);
    set.add(2137, 0);
    set.remove(2);
    set.remove(3, 5);
    set.remove(4, 4);
    set.remove(5, 2);
    set.remove(1, 0);
    assertEquals(1, set.count(1));
    assertEquals(1, set.count(2));
    assertEquals(0, set.count(3));
    assertEquals(0, set.count(4));
    assertEquals(3, set.count(5));
  }

  @Test
  void iterateTest() {
    MultiSet<Integer> set = new MultiSet<>();
    set.add(1);
    set.add(2,2);
    set.add(3,3);
    set.add(4, 4);
    set.add(5,5);
    set.remove(2137);
    set.add(2137, 0);
    set.remove(2);
    set.remove(3, 5);
    set.remove(4, 4);
    set.remove(5, 2);
    set.remove(1, 0);
    String expected = "12555";
    String actual = "";
    var iter = set.iterator();
    while (iter.hasNext())
      actual+=iter.next().toString();
    assertEquals(expected, actual);
  }





}
