package Problems;
import adt.Position;
import data_structure.LinkedBinaryTree;

public class ArrayToBinaryTree {
    public static void main(String[] args) {
        Character[] arr = {'/', '*', '+', '+', '4', '-', '2', '3', '1', null, null, '9', '5', null, null};
        LinkedBinaryTree<Character> tree = createTree(arr);
        for (Position<Character> c : tree.breadthFirst()) {
            System.out.print(c.getElement()+" ");
        }
    }

    // TODO do the valdiation
    @SuppressWarnings("all")
    public static <T> LinkedBinaryTree<T> createTree(T[] arr) throws IllegalArgumentException {
        LinkedBinaryTree<T> tree = new LinkedBinaryTree<>();
        Position<T>[] positions = new Position[arr.length];
        positions[0] = tree.addRoot(arr[0]);


        for (int i = 1; i < arr.length; i++) {

            int parentIndex = (i - 1) / 2;

            if (arr[i] == null)
                continue;

            if (i % 2 == 0) {
                positions[i] = tree.addRight(positions[parentIndex], arr[i]);
            } else {
                positions[i] = tree.addLeft(positions[parentIndex], arr[i]);
            }

        }
        return tree;
    }
}
