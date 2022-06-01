package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

class MultisetTest {
    @Test 
    void addingAndRemovingSingleElement() {
        var multiSet = new Multiset<Integer>();
        multiSet.add(1);
        multiSet.add(1);
        multiSet.add(1);
        multiSet.add(2);
        multiSet.add(1);
        multiSet.add(2);
        multiSet.add(3);
        multiSet.remove(4);
        multiSet.remove(2);
        multiSet.remove(1);
        multiSet.remove(1);
        multiSet.remove(3);
        multiSet.remove(3);
        assertEquals(multiSet.count(1), 2);
        assertEquals(multiSet.count(2), 1);
        assertEquals(multiSet.count(3), 0);
        assertEquals(multiSet.count(490), 0);
    }

    @Test 
    void addingAndRemovingMultipleElements() {
        var multiSet = new Multiset<Integer>();
        multiSet.add(2,1);
        multiSet.add(1,5);
        multiSet.add(2,1);
        multiSet.add(3,7);
        multiSet.add(1,3);
        multiSet.remove(1,6);
        multiSet.remove(1);
        multiSet.remove(3,20);
        assertEquals(multiSet.count(1), 1);
        assertEquals(multiSet.count(2), 2);
        assertEquals(multiSet.count(3), 0);
        assertEquals(multiSet.count(490), 0);
    }
    @Test
    void equalsTest() {
        var multiSet1 = new Multiset<Integer>();
        var multiSet2 = new Multiset<Integer>();
        var multiSet3 = new Multiset<Integer>();
        var multiSet4 = new Multiset<Integer>();
        var multiSet5 = new Multiset<Integer>();
        var set = new HashSet<Integer>();
        multiSet1.add(1,1);
        multiSet2.add(1,1);
        multiSet3.add(1,2);
        multiSet4.add(1,1);
        multiSet4.add(2,1);
        multiSet5.add(1,3);
        multiSet5.remove(1,2);
        set.add(1);
        assertEquals(multiSet1.equals(multiSet2), true);
        assertEquals(multiSet2.equals(multiSet5), true);
        assertEquals(multiSet3.equals(multiSet2), false);
        assertEquals(multiSet1.equals(multiSet4), false);
        assertEquals(multiSet1.equals(set), false);
    }
}
