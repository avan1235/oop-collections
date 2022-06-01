package pl.edu.mimuw.collections;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public final class Multiset <E> implements IMultiset <E>
{
	private final TreeMap <E, Integer> internalMap;
	private int size;

	public Multiset()
	{
		this.internalMap = new TreeMap <>();
	}

	public Multiset(TreeMap <E, Integer> internalMap)
	{
		this.internalMap = internalMap;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public int count(E element)
	{
		if (!internalMap.containsKey(element))
			return 0;
		return internalMap.get(element);
	}

	@Override
	public int add(E element, int occurrences)
	{
		size += occurrences;
		if (!internalMap.containsKey(element))
			internalMap.put(element, occurrences);
		internalMap.replace(element, internalMap.get(element) + occurrences);
		return internalMap.get(element);
	}

	@Override
	public boolean add(E element)
	{
		size++;
		if (!internalMap.containsKey(element))
		{
			internalMap.put(element, 1);
			return true;
		}
		internalMap.replace(element, internalMap.get(element) + 1);
		return false;
	}

	@Override
	public int remove(E element, int occurrences)
	{
		if (!internalMap.containsKey(element))
			return 0;
		if (occurrences >= internalMap.get(element))
		{
			size -= internalMap.get(element);
			internalMap.remove(element);
			return 0;
		}
		size -= occurrences;
		internalMap.replace(element, internalMap.get(element) - occurrences);
		return internalMap.get(element);
	}

	@Override
	public boolean remove(E element)
	{
		if (!internalMap.containsKey(element))
			return false;
		size -= internalMap.get(element);
		internalMap.remove(element);
		return true;
	}

	@Override
	public Set <E> elementSet()
	{
		return internalMap.keySet();
	}

	@Override
	public boolean contains(E element)
	{
		return internalMap.containsKey(element);
	}

	@Override
	public Iterator <E> iterator()
	{
		return new Iterator<E>()
		{
			private final Iterator<E> it = Multiset.this.internalMap.keySet().iterator();

			private E element = it.next();
			private int elementIndex = 0;

			@Override
			public boolean hasNext()
			{
				if (it.hasNext())
					return true;
				else return elementIndex < internalMap.get(element);
			}

			@Override
			public E next()
			{
				if (element == null)
					return null;
				if (elementIndex < internalMap.get(element))
				{
					elementIndex++;
					return element;
				}
				elementIndex = 1;
				return element = it.next();
			}
		};
	}

	@Override
	public String toString()
	{
		var sb = new StringBuilder();
		sb.append("Multiset: ");
		var it = iterator();
		while (it.hasNext())
			sb.append(it.next()).append(" ");
		return sb.toString();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Multiset <?> multiset = (Multiset <?>) o;
		return size == multiset.size && Objects.equals(internalMap, multiset.internalMap);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(internalMap, size);
	}
}