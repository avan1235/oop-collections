package pl.edu.mimuw.collections;

import java.util.*;

public class MultiSet<E> implements IMultiset<E> {
  private int size; //rozmiar wielozbioru
  private final HashMap<E, Integer> elements; //elementy w formie hashmapy elementow na ich liczby wystąpień w zbiorze

  //Podstawowe kontruktory
  public MultiSet(HashMap<E, Integer> elements) {
    this.elements = elements;
    this.size = 0;
    for (var key : elements.keySet()) {
      this.size += this.elements.get(key);
    }
  }

  public MultiSet(Set<E> set) {
    this();
    for (var elem : set) {
      this.elements.put(elem, 1);
    }
  }

  public MultiSet() {
    this.size = 0;
    this.elements = new HashMap<>();
  }

  @Override
  public int size() {
    return this.size;
  }//wykorzystanie definicji i własności struktury

  @Override
  public int count(E element) {
    return elements.get(element);
  }//wykorzystanie definicji i własności struktury

  @Override
  public int add(E element, int occurrences) { //obsługa dodania odpowieniej ilości wystąpnień danego elementu
    this.elements.put(element, occurrences);
    this.size += occurrences; //w tym z katualizacją rozmiaru wielozbioru
    return this.count(element);
  }

  @Override
  public boolean add(E element) { //dodawanie pojedynczego elementu
    this.size++;
    if (this.contains(element)) { //przypadek w ktorym element juz jest w zbiorze: wtedy zwiekszenie jego liczebnosci o 1 i zwrócenie false
      this.elements.put(element, this.elements.get(element) + 1);
      return false;
    }
    this.elements.put(element, 1);//w przeciwnym wypadku po prostu dodanie elementu z wartoscia 1 do hashmapy i zwrócenie true
    return true;
  }

  @Override
  public int remove(E element, int occurrences) { //ususnięcie danej liczebnosci
    if (!this.contains(element)) return 0;
    if (this.elements.get(element) >= occurrences) this.size -= occurrences;
    else this.size -= this.elements.get(element);
    this.elements.put(element, this.elements.get(element) - occurrences); //odpowienie zmieniejszenie liczebnosci
    if (this.elements.get(element) <= 0) { //jezeli unuwane jest tyle co lub wiecej niz jest t uniniecie elementu z mapy
      this.elements.remove(element);
      return 0;
    }
    return this.elements.get(element);
  }

  @Override
  public boolean remove(E element) { //ususniecie wsyzstkich instancji danego elementu z wielozbioru
    if (this.contains(element)) {
      this.size -= this.elements.get(element);
      this.elements.remove(element);
      return true;
    }
    return false;
  }

  @Override
  public Set<E> elementSet() { ////zwrocenie klona zbioru elementow jako obiekt typu HashSet
    return new HashSet<E>(this.elements.keySet());
  }

  @Override
  public boolean contains(E element) {
    return this.elements.containsKey(element);
  } //wykorzystanie metody wbudowanej


  @Override
  public Iterator<E> iterator() { //obsluga iteratora
    return new Iterator<E>() {
      private E currElem = MultiSet.this.elements.keySet().iterator().next(); //inicjalizacja pierwszego elementu za pomocą metody wbudowanej
      private int currElemCount;

      @Override
      public boolean hasNext() { //sprawdzenie czy nie przekroczyło się licznika danego elementu ewentualnie czy nastęny element w rozumniu iteratora hashmapy jest dostępny
        return elements != null && elements.get(currElem) != null
          && ((currElemCount < elements.get(currElem)
          || elements.keySet().iterator().next() != null));
      }

      @Override
      public E next() { //sprawdzenie czy nastepny obiekt moze byc tego samego tyu i jezeli tak to zwrocenie go
        if (currElemCount < elements.get(currElem)) {
          System.out.println();
          currElemCount++;
          return currElem;
        }
        currElem = elements.keySet().iterator().next(); //w przeciwnym wypadku skorzystanie z iteratora kluczy HashMapy
        currElemCount = 1;
        return currElem;
      }
    };
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MultiSet<?> multiSet = (MultiSet<?>) o;
    return size == multiSet.size && Objects.equals(elements, multiSet.elements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(size, elements);
  }

  @Override
  public String toString() { // czytelne wypisanie reprezentacji wielozbioru
    var sb = new StringBuilder();
    sb.append("Multiset with elements: {\n");
    for (var key : this.elements.keySet()) {
      sb.append(key.toString()).append(" with multiplicity: ").append(elements.get(key)).append('\n');
    }
    return sb.append('}').toString();
  }
}
