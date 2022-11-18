package Problems;

import adt.Position;
import data_structure.LinkedBinaryTree;

public class ArrayToBinaryTree {
    public static void main(String[] args) {
        Character[] arr = {'/', '*', '+', '+', '4', '-', '2', '3', '1', null, null, '9', '5', null, null};
        LinkedBinaryTree<Character> tree = createTree(arr);
        for (Position<Character> c : tree.breadthFirst()) {
            System.out.print(c.getElement() + " ");
        }
        System.out.println();

        Character[] invalid = {null, '*', '+', '+', '4', '-', '2', '3', '1', null, null, '9', '5', null, null};
        try {
            LinkedBinaryTree<Character> invalidTree = createTree(invalid);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Tree");
        }

        Character[] invalid2 = {'/', null, '+', '+', '4', '-', '2', '3', '1', null, null, '9', '5', null, null};
        try {
            LinkedBinaryTree<Character> invalidTree = createTree(invalid2);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Tree another");
        }


        Character[] valid = {null, null, null};
        LinkedBinaryTree<Character> validEmpty = createTree(valid);
        System.out.println(validEmpty.size());


        Character[] valid2 = {};
        LinkedBinaryTree<Character> validEmpty2 = createTree(valid2);
        System.out.println(validEmpty2.size());
    }

    @SuppressWarnings("all")
    public static <T> LinkedBinaryTree<T> createTree(T[] arr) throws IllegalArgumentException {
        validate(arr);

        LinkedBinaryTree<T> tree = new LinkedBinaryTree<>();
        if (arr.length == 0) return tree;

        T root = arr[0];
        if (root == null) return tree;

        Position<T>[] positions = new Position[arr.length];
        positions[0] = tree.addRoot(root);

        for (int i = 1; i < arr.length; i++) {
            int parentIndex = (i - 1) / 2;

            if (arr[i] == null) continue;

            if (i % 2 == 0) {
                positions[i] = tree.addRight(positions[parentIndex], arr[i]);
            } else {
                positions[i] = tree.addLeft(positions[parentIndex], arr[i]);
            }

        }
        return tree;
    }

    private static <T> void validate(T[] arr) throws IllegalArgumentException {
        if (arr.length == 0)
            return;

        T root = arr[0]; // all the element should be null

        for (int i = 1; i < arr.length; i++) {
            if (root == null) {
                if (arr[i] != null) throw new IllegalArgumentException("not a valid Array");
                continue;
            }


            int parentIndex = (i - 1) / 2;
            if (arr[parentIndex] == null) throw new IllegalArgumentException("not a valid Array");
        }
    }
}
