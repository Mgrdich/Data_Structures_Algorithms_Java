import data_structure.CircularlyLinkedList;
import data_structure.SinglyLinkedList;
import sorting.CountingSort;
import util.Util;

public class Test {
    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 5, 6, 7, 89, 9};
        CountingSort.sort(arr);
        Util.arrayPrint(arr);
    }
}
