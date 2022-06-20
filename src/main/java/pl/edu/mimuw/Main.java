package pl.edu.mimuw;

import pl.edu.mimuw.collections.MultiMap;
import pl.edu.mimuw.collections.MultiSet;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    MultiMap<Integer, String> mapqa = new MultiMap<>();
    ArrayList<String> ipp = new ArrayList<>();{
      ipp.add("INDYWIDUALNY");
      ipp.add("PROJEKT");
      ipp.add("PROGRAMISTYCZNY");
    }
    ArrayList<String> opis = new ArrayList<>();{
      opis.add("nie");
      opis.add("jest");
      opis.add("zart");
    }

    mapqa.put(1, "Bardzo");
    mapqa.put(3, "to");
    mapqa.putAll(2, ipp);
    mapqa.put(1, "lubie");
    mapqa.putAll(3, opis);
    mapqa.put(4, "kappa");
    mapqa.removeAll(4);
    mapqa.remove(7, "Hello World");
    System.out.println(mapqa);
    // Wiem że trochę po Yodowemu ale chodziło raczej o poprawność wypisywania i że wszystko jest.



    MultiSet<Integer> secique = new MultiSet<>();
    secique.add(4, 4);
    secique.add(14369, 2);
    secique.add(146543);
    secique.add(14369);
    secique.add(4, 2);
    secique.remove(2137, 9);
    secique.remove(420);
    System.out.println(secique);



    // throw new IllegalStateException("TODO: show your toString() implementation on simple examples");
  }
}
