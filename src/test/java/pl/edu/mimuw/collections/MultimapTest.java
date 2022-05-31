package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultimapTest {

  @Test
  void addRemoveTest(){
    final var map = new MultiMap<>();

    map.put(1,'a');
    map.put(2,'c');
    map.put(3,'a');
    map.put(2,'a');
    map.put(1,'x');
    map.put(3,'c');
    map.put(1,'v');
    map.put(1,'a');
    assertEquals(map.size(),8);

    map.remove(1,'a');
    assertEquals(map.size(),7);

    map.removeAll(3);
    assertEquals(map.size(),5);
  }

  @Test
  void containsTest(){
    final var map = new MultiMap<>();

    map.put(1,'a');
    map.put(2,'c');
    map.put(3,'a');
    map.put(2,'a');
    map.put(1,'x');
    map.put(3,'c');
    map.put(1,'v');
    map.put(1,'a');

    assertTrue(map.containsKey(1));
    assertFalse(map.containsKey(4));
    assertTrue(map.containsValue('x'));
    assertTrue(map.containsValue('a'));
    assertFalse(map.containsValue('h'));
  }

}
