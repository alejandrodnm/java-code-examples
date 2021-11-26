package codeexamples.designpatterns.adapter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public interface Sorter {
  int[] sort(final int[] p0);

  public static class NumberSorter {
    public List<Integer> sort(final List<Integer> items) {
      final LinkedList<Integer> sortedList = new LinkedList<Integer>(items);
      sortedList.sort((x, y) -> y - x);
      return sortedList;
    }
  }

  public class NumberSorterAdapter implements Sorter {
    @Override
    public int[] sort(final int[] numbers) {
      final List<Integer> numbersList = Arrays.stream(numbers).boxed().toList();
      return new NumberSorter().sort(numbersList).stream().mapToInt(i -> i).toArray();
    }
  }
}
