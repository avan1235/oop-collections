package pl.edu.mimuw.collections;

import java.util.Iterator;
import java.util.Set;

public interface IMultiset<E>
{

	int size();

	int count(E element);

	int add(E element, int occurrences);

	boolean add(E element);

	int remove(E element, int occurrences);

	boolean remove(E element);

	Set <E> elementSet();

	boolean contains(E element);

	Iterator <E> iterator();

	@Override
	boolean equals(Object object);

	@Override
	int hashCode();

	@Override
	String toString();
}