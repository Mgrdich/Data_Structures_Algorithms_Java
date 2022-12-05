package Problems;

import adt.Position;
import data_structure.LinkedBinaryTree;

public class ArrayToBalancedBST {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        LinkedBinaryTree<Integer> tree = getBalancedBST(arr);
        System.out.println(toStringInOrderTree(tree));

        System.out.println(BalancedBinaryTree.isBalanced(tree));
    }


    public static LinkedBinaryTree<Integer> getBalancedBST(int[] arr) {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        if (arr.length == 0) return tree;


        // in order not to handle it in the recursive method
        int low = 0;
        int high = arr.length - 1;
        int mid = (low + high) / 2;
        Position<Integer> position = tree.addRoot(arr[mid]);
        getBalancedBST(arr, tree, position, 0, mid - 1, true);
        getBalancedBST(arr, tree, position, mid + 1, high, false);

        return tree;
    }

    private static void getBalancedBST(int[] arr, LinkedBinaryTree<Integer> tree, Position<Integer> position, int low, int high, boolean isToLeft) {

        if (low > high) {
            // base case
            return;
        }

        Position<Integer> newPosition;

        int mid = (low + high) / 2;

        if (isToLeft) {
            newPosition = tree.addLeft(position, arr[mid]);
        } else {
            newPosition = tree.addRight(position, arr[mid]);
        }

        getBalancedBST(arr, tree, newPosition, low, mid - 1, true);
        getBalancedBST(arr, tree, newPosition, mid + 1, high, false);
    }

    public static <E> String toStringInOrderTree(LinkedBinaryTree<E> tree) {
        StringBuilder str = new StringBuilder("(");
        for (Position<E> position : tree.inOrder()) {
            str.append(position.getElement()).append(", ");
        }
        str.delete(str.length() - 2, str.length());
        str.append(")");
        return str.toString();
    }
}
