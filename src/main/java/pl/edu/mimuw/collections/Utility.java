package pl.edu.mimuw.collections;

import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Utility {
  private Utility() {}

  public static <T> Stream<T> toStream(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
  }

  public static boolean areStreamsEqualMultisets(Stream<?> a, Stream<?> b) {
    var elementsA = new HashMap<Object, Integer>();
    var elementsB = new HashMap<Object, Integer>();
    a.forEach(i -> elementsA.merge(i, 1, Integer::sum));
    b.forEach(i -> elementsB.merge(i, 1, Integer::sum));
    return elementsA.equals(elementsB);
  }
}
