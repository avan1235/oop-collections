package pl.edu.mimuw.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultisetTest
{
	@Test
	void testElementCount()
	{
		var multiset = new Multiset <Integer>();

		multiset.add(1);
		multiset.add(1);
		multiset.add(1);

		assertEquals(3, multiset.count(1));
	}

	@Test
	void testAddingInBulkVsAddingOneAtATime()
	{
		var multiset1 = new Multiset <Integer>();
		var multiset2 = new Multiset <Integer>();

		multiset1.add(1);
		multiset1.add(1);
		multiset1.add(1);
		multiset1.add(2);
		multiset1.add(2);

		multiset2.add(1, 3);
		multiset2.add(2, 2);

		assertEquals(multiset1, multiset2);
	}

	@Test
	void testRemovingElements()
	{
		var multiset = new Multiset <Integer>();

		multiset.add(1);
		multiset.add(2, 2);
		multiset.add(3, 3);

		multiset.remove(1);
		assertEquals(0, multiset.count(1));

		multiset.remove(2, 2);
		assertEquals(0, multiset.count(2));

		multiset.remove(3, 2);
		assertEquals(1, multiset.count(3));

		multiset.remove(4, 20);
		assertEquals(0, multiset.count(4));
	}
}