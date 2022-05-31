package pl.edu.mimuw.test;

import org.testng.annotations.Test;
import pl.edu.mimuw.collections.MultiSet;

public class SetTest {

  @Test
  public void multiSetTest1() {
    MultiSet<Integer> set = new MultiSet<>();
    assert set.add(7, 2) == 2;
    assert set.add(11, 1) == 1;
    assert set.add(9);
    assert set.contains(7) && set.contains(9) && set.contains(11);
    assert set.count(7) == 2 && set.count(9) == 1 && set.count(11) == 1;
  }

  @Test
  public void multiSetTest2() {
    MultiSet<String> set = new MultiSet<>();
    assert set.add("", 10) == 10;
    assert set.add(".");
    assert !set.remove(" ");
    assert set.remove(".");
    assert set.remove("");
    assert set.size() == 0;
  }

  @Test
  public void multiSetTest3() {
    MultiSet<Object> set = new MultiSet<>();
    assert set.add("");
    assert set.add(18.3, 22) == 22;
    assert set.add(1);
    assert set.size() == 24;
    assert set.remove(18.3, 10) == 12;
    assert set.size() == 14;
    assert set.remove(1, 2) == 0;
    assert !set.contains(1);
    assert set.contains(18.3);
    assert set.contains("");
    assert set.remove("");
    assert set.remove(18.3, 12) == 0;
    assert !set.contains("") && !set.contains(".") && !set.contains(18.3) && !set.contains(1);
    assert set.size() == 0;
  }

}
