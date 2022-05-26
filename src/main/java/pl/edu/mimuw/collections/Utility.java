package pl.edu.mimuw.collections;

import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Utility {
  private Utility() {}

  public static <T> Stream<T> toStream(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
  }
}
