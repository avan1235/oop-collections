package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MultimapTest
{
	@Test
	void testRemoveAll()
	{
		var multimap = new Multimap<Integer, Integer>();

		multimap.put(1, 1);
		multimap.put(1, 11);
		multimap.put(1, 111);
		multimap.put(2, 22);
		multimap.put(2, 111);
		multimap.put(3, 11);
		multimap.put(3, 22);
		multimap.put(3, 33);

		multimap.removeAll(1);
		multimap.removeAll(2);
		multimap.removeAll(3);

		assertFalse(multimap.containsKey(1));
		assertFalse(multimap.containsKey(2));
		assertFalse(multimap.containsKey(3));

		assertEquals(0, multimap.size());

		multimap.removeAll(4);

		assertFalse(multimap.containsKey(4));
		assertEquals(0, multimap.size());
	}

	@Test
	void testCorrectOrder()
	{
		var multimap = new Multimap<Integer, Integer>();

		multimap.put(1, 2);
		multimap.put(1, 1);
		multimap.put(1, 3);

		var exp = new ArrayList<Integer>();

		exp.add(2);
		exp.add(1);
		exp.add(3);

		assertEquals(exp, multimap.get(1));
	}

	@Test
	void testKeySetGeneration()
	{
		var multimap = new Multimap<Integer, Integer>();

		multimap.put(1, 1);
		multimap.put(1, 11);
		multimap.put(1, 111);
		multimap.put(2, 22);
		multimap.put(2, 111);
		multimap.put(3, 11);
		multimap.put(3, 22);
		multimap.put(3, 33);

		assertEquals(multimap.keys().elementSet(), multimap.keySet());
	}
}