package pl.edu.mimuw;

import pl.edu.mimuw.collections.Multiset;

import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main {

  public static void main(String[] args) {
    final var integerMultiset = new Multiset<Integer>();

    integerMultiset.add(1, 5);
    integerMultiset.add(2, 5);
    integerMultiset.add(3, 5);
    integerMultiset.remove(1, 4);
    integerMultiset.remove(2, 3);
    integerMultiset.remove(3, 2);
    System.out.println(integerMultiset);

    final var multisetMultiset = new Multiset<Multiset<Integer>>();
    multisetMultiset.add(integerMultiset, 5);
    multisetMultiset.remove(integerMultiset);
    System.out.println(multisetMultiset);

    final var myFavouriteExceptions = new Multiset<Exception>();
    myFavouriteExceptions.add(new NullPointerException("I like NPE the most"), 3);
    myFavouriteExceptions.add(new IndexOutOfBoundsException(), 1);
    System.out.println(myFavouriteExceptions);

    final var bigMultiset = new Multiset<Multiset<Integer>>();
    bigMultiset.add(integerMultiset, 420000000);
    System.out.println(bigMultiset);
  }
}
