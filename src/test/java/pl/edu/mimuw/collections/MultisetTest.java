package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultisetTest {

  @Test
  void sizeTest(){

    final Multiset<Integer> set = new Multiset<>();

    for(int i=0;i<50;i++){
      set.add(i);
    }
    assertEquals(set.size(), 50);


    for(int i=0;i<30;i++){
      set.add(1);
    }
    assertEquals(set.size(), 80);

    set.remove(5,10);
    assertEquals(set.size(), 79);

    set.remove(1, 21);
    assertEquals(set.size(), 58);
  }

  @Test
  void countAndContainsTest(){
    final Multiset<Integer> set = new Multiset<>();

    for(int i=0;i<50;i++){
      set.add(i);
    }
    assertEquals(set.count(1), 1);
    assertTrue(set.contains(1));


    for(int i=0;i<30;i++){
      set.add(1);
    }
    assertEquals(set.count(1), 31);

    set.remove(5,10);
    assertEquals(set.count(5), 0);
    assertFalse(set.contains(5));

    set.remove(1, 21);
    assertEquals(set.count(1), 10);
  }

}
