package pl.edu.mimuw.test;

import org.testng.annotations.Test;
import pl.edu.mimuw.collections.MultiMap;

import java.util.ArrayList;

public class MapTest {
  @Test
  public void MultiMapTest1() {
    MultiMap<Integer, String> map = new MultiMap<>();
    assert map.put(1, "1")
      && map.put(2, "2")
      && map.put(3, "3");

    assert !map.remove(4, "4");
    assert !map.containsKey(5);
    assert map.size() == 3;
  }

  @Test
  public void MultiMapTest2() {
    MultiMap<Integer, String> map = new MultiMap<>();
    assert map.put(1, "1")
      && !map.put(1, "2")
      && !map.put(1, "3");

    assert !map.remove(1, "4");
    assert !map.containsValue("4");
    assert map.remove(1, "1");
    assert map.size() == 1;
  }

  @Test
  public void MultiMapTest3() {
    MultiMap<Object, Object> map = new MultiMap<>();
    assert map.put(1, "1")
      && map.put(18, 2.8)
      && !map.put(18, 19);
    assert map.remove(1, "1");
    var valueList = new ArrayList<>(map.removeAll(18));
    assert valueList.size() == 2 && map.size() == 0;
  }
}
