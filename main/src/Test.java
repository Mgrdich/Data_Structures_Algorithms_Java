import sorting.BubbleSort;
import sorting.InsertionSort;
import sorting.QuickSort;
import sorting.SelectionSort;
import util.Util;

public class Test {
    public static void main(String[] args) {
        Integer[] arr = {3, 2, 5, -1, 6, 10, 5};
        QuickSort.nonMemoryOptimizedSort(arr);
        Util.arrayPrint(arr);
    }
}
