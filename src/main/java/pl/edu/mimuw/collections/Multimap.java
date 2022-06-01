package pl.edu.mimuw.collections;

import java.util.*;

import static java.util.List.of;

public class Multimap<K, V> implements IMultimap <K, V>
{
	private final TreeMap <K, ArrayList <V>> internalMap;
	private int size;

	public Multimap()
	{
		this.internalMap = new TreeMap <>();
		size = 0;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean containsKey(K key)
	{
		return internalMap.containsKey(key);
	}

	@Override
	public boolean containsValue(V value)
	{
		for (var i : internalMap.values())
			if (i.contains(value))
				return true;
		return false;
	}

	@Override
	public boolean put(K key, V value)
	{
		if (internalMap.containsKey(key) && internalMap.get(key).contains(value))
			return false;
		size++;
		if (!internalMap.containsKey(key))
		{
			var tmp = new ArrayList <V>();
			tmp.add(value);
			internalMap.put(key, tmp);
		}
		else (internalMap.get(key)).add(value);
		return true;
	}

	@Override
	public boolean remove(K key, V value)
	{
		if (internalMap.containsKey(key) && internalMap.get(key).contains(value))
		{
			size--;
			internalMap.get(key).remove(value);
			return true;
		}
		return false;
	}

	@Override
	public boolean putAll(K key, Iterable <? extends V> values)
	{
		boolean ret = false;
		for (V value : values)
			if (!internalMap.containsKey(key) || !internalMap.get(key).contains(value))
			{
				size++;
				ret = true;
			}
		for (V value : values)
		{
			if (internalMap.containsKey(key))
				internalMap.get(key).add(value);
			else
			{
				var tmp = new ArrayList <V>();
				tmp.add(value);
				internalMap.put(key, tmp);
			}
		}
		return ret;
	}

	@Override
	public Collection <V> removeAll(K key)
	{
		ArrayList <V> ret;
		if (internalMap.containsKey(key))
			ret = (ArrayList <V>) ((internalMap.get(key))).clone(); //chyba nie da się tutaj zrobić deep copy w bardziej elegancki sposób
		else ret = new ArrayList <>();
		size -= ret.size();
		internalMap.remove(key);
		return ret;
	}

	@Override
	public Collection <V> get(K key)
	{
		return internalMap.get(key);
	}

	@Override
	public Set <K> keySet()
	{
		return internalMap.keySet();
	}

	@Override
	public IMultiset <K> keys()
	{
		var ret = new Multiset <K>();
		for (var i : keySet())
			ret.add(i, internalMap.get(i).size());
		return ret;
	}

	@Override
	public String toString()
	{
		var sb = new StringBuilder();
		sb.append("Multimap:\n");
		for (var i : keySet())
		{
			sb.append(i).append(": ");
			for (var j : internalMap.get(i))
				sb.append(j).append(" ");
			sb.append('\n');
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Multimap <?, ?> multimap = (Multimap <?, ?>) o;
		return size == multimap.size && Objects.equals(internalMap, multimap.internalMap);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(internalMap, size);
	}
}