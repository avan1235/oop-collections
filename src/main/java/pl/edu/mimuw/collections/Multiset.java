package pl.edu.mimuw.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Multiset<E> implements IMultiset<E>, Iterable<E> {
    
    private Map<E, Integer> occurencesMap;
    private int size;

    public Multiset() {
        this.size = 0;
        this.occurencesMap = new HashMap<>();
    }   
    public int size() {
        return size;
    }
    
    public int count(E element) {
        if(occurencesMap.containsKey(element)) {
            return occurencesMap.get(element);
        }
        return 0;
    }

    public int add(E element, int occurrences) {
        if(!occurencesMap.containsKey(element)) {
            occurencesMap.put(element, occurrences);
            return occurrences;
        }
        int prevOccurences = occurencesMap.get(element);
        occurencesMap.put(element, occurrences + prevOccurences);
        return occurrences + prevOccurences;
    }

    public boolean add(E element) {
        if(!occurencesMap.containsKey(element)) {
            occurencesMap.put(element, 1);
            return true;
        }
        int prevOccurences = occurencesMap.get(element);
        occurencesMap.put(element, prevOccurences + 1);
        return true;
    }

    public boolean remove(E element) {
        if(!occurencesMap.containsKey(element)) {
            return false;
        }
        int prevOccurences = occurencesMap.get(element);
        occurencesMap.put(element, Math.max(0, prevOccurences - 1));
        return (Math.max(0, prevOccurences - 1) > 0);
    }

    public int remove(E element, int occurrences) {
        if(!occurencesMap.containsKey(element)) {
            return 0;
        }
        int prevOccurences = occurencesMap.get(element);
        occurencesMap.put(element, Math.max(0, prevOccurences - occurrences));
        return Math.max(0, prevOccurences - occurrences);
    }

    public Set<E> elementSet() {
        Set<E> s = new HashSet<>();
        for (Map.Entry<E, Integer> entry : occurencesMap.entrySet()) {
            if(entry.getValue() != 0) s.add(entry.getKey());
        }
        return s;
    }

    public boolean contains(E element) {
        if(!occurencesMap.containsKey(element)) {
            if(occurencesMap.get(element) != 0) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean equals(Object object) {
        if(object.getClass() == this.getClass()) {
            return object.hashCode() == this.hashCode();
        }
        return false;
    }

    public Iterator<E> iterator() {
        return new Iterator<>() {
            Iterator<Map.Entry<E, Integer>> mapEntryIterator = occurencesMap.entrySet().iterator();
            int remainingOccurences = 0;
            Map.Entry<E, Integer> currentElement;
            public boolean hasNext() {
                if(remainingOccurences <= 0) return mapEntryIterator.hasNext();
                return true;
            }
            public E next() {
                if(remainingOccurences <= 0) {
                    currentElement = mapEntryIterator.next();
                    remainingOccurences = Multiset.this.count(currentElement.getKey());
                }
                remainingOccurences--;
                return currentElement.getKey();
            }
        };
    }
  
    @Override
    public int hashCode() {
        return Objects.hash(this.toString(), this.getClass());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Multiset:\n{\n");
        for(Map.Entry<E, Integer> entry : occurencesMap.entrySet()) {
            if(entry.getValue() != 0) {
                s.append(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        }
        s.append("}");
        return s.toString();
    }
}