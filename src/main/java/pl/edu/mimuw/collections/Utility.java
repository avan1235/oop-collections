package pl.edu.mimuw.collections;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Utility {
  private Utility() {}

  public static <T> Stream<T> toStream(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
  }

  public static boolean areStreamsEqualMultisets(Stream<?> a, Stream<?> b) {
    var elementsA = a.collect(Collectors.toList());
    var elementsB = b.collect(Collectors.toList());
    if (elementsA.size() != elementsB.size()) return false;
    return new HashSet<>(elementsA).equals(new HashSet<>(elementsB));
  }
}
