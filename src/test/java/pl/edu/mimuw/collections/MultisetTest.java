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
	void testEquals()
	{
		var multiset1 = new Multiset <Integer>();
		var multiset2 = new Multiset <Integer>();

//		multiset1.add(1);
//		multiset1.add(1);
//		multiset1.add(1);
//		multiset1.add(2);
//		multiset1.add(2);

//		multiset2.add(1, 3);
		multiset2.add(2, 1);

		assertEquals(multiset1, multiset2);
	}
}