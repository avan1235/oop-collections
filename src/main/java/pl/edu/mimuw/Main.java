package pl.edu.mimuw;

import pl.edu.mimuw.collections.Multimap;
import pl.edu.mimuw.collections.Multiset;

public class Main
{

	public static void main(String[] args)
	{
		var multiset = new Multiset <Integer>();
		multiset.add(1);
		multiset.add(2, 2);
		multiset.add(3, 3);
		multiset.add(21, 37);
		System.out.println(multiset);

		var multimap = new Multimap <Integer, Integer>();
		for (int i = 1; i <= 5; i++)
			for (int j = 1; j <= 5; j++)
				multimap.put(i, i * j);
		System.out.println(multimap);
	}
}