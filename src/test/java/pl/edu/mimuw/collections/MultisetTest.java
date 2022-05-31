package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultisetTest {

  @Test
  void sizeTest() {
    MultiSet<Integer> integers = new MultiSet<>();
    integers.add(21, 37);
    integers.add(4, 20);
    assertEquals(integers.size(), 57);
    integers.add(69, 2115);
    assertEquals(integers.size(), 2172);
  }

  @Test
  void iteratorTest() {
    MultiSet<Boolean> booleans = new MultiSet<>();
    booleans.add(true, 3180);
    booleans.add(false, 519);
    int counter = 0;
    for (var v : booleans) {
      if (v) counter++;
    }
    assertEquals(counter, 3180);
  }

  @Test
  void removeTest() {
    MultiSet<Character> characters = new MultiSet<>();
    characters.add('a', 44);
    characters.remove('a', 22);
    assertEquals(characters.count('a'), 22);
    characters.remove('a');
    assertEquals(characters.count('a'), 21);
  }
}
