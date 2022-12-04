package util;

import adt.List;
import adt.Position;
import adt.Queue;
import data_structure.ArrayList;
import data_structure.LinkedBinaryTreeNode;
import data_structure.LinkedQueue;

public class Util {
    public static <T> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * a helper method does print all the elements in the array
     * Algorithmic complexity is O(n) since it iterates over the array once
     * n is the size of the array
     *
     * @param arr and array that you want to print
     */
    public static <T> void arrayPrint(T[] arr) {
        for (T t : arr) {
            System.out.print(t + " ");
        }
    }

    public static int getMax(Integer[] arr) {
        int max = arr[0];

        for (int j : arr) {
            if (j > max) {
                max = j;
            }
        }

        return max;
    }

    public static int getMin(Integer[] arr) {
        int min = arr[0];

        for (int j : arr) {
            if (j < min) {
                min = j;
            }
        }

        return min;
    }

    public static int binarySearch(int[] arr, int target) {
        int min = 0;
        int max = arr.length - 1;


        while (min <= max) {
            int mid = min + (max - min) / 2;
            int diff = arr[mid] - target;

            if (diff == 0) return mid;

            if (diff > 0) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    public static <E> void swapLinkedBinaryNodes(LinkedBinaryTreeNode<E> node, LinkedBinaryTreeNode<E> anotherNode) {
        LinkedBinaryTreeNode<E> nodeParent = node.getParent();
        LinkedBinaryTreeNode<E> tempAnotherNodeParent = anotherNode.getParent();

        // root case
        if (nodeParent != null) {
            if (nodeParent.getLeft() == node) {
                nodeParent.setLeft(anotherNode);
            } else {
                nodeParent.setRight(anotherNode);
            }
        }

        // root case
        if (tempAnotherNodeParent != null) {
            if (tempAnotherNodeParent.getLeft() == anotherNode) {
                tempAnotherNodeParent.setLeft(node);
            } else {
                tempAnotherNodeParent.setRight(node);
            }
        }

        LinkedBinaryTreeNode<E> tempLeft = node.getLeft();
        LinkedBinaryTreeNode<E> tempRight = node.getRight();
        E tempElement = node.getElement();

        node.setLeft(anotherNode.getLeft());
        node.setRight(anotherNode.getRight());
        node.setParent(tempAnotherNodeParent);
        node.setElement(anotherNode.getElement());


        anotherNode.setLeft(tempLeft);
        anotherNode.setRight(tempRight);
        anotherNode.setParent(nodeParent);
        anotherNode.setElement(tempElement);
    }

    public static <E> Iterable<E> breadthFirstTraversal(LinkedBinaryTreeNode<E> node) {
        ArrayList<E> list = new ArrayList<>();

        Queue<LinkedBinaryTreeNode<E>> fringe = new LinkedQueue<>();
        fringe.enqueue(node);
        while (!fringe.isEmpty()) {
            LinkedBinaryTreeNode<E> p = fringe.dequeue();
            list.add(p.getElement());

            if (p.getLeft() != null)
                fringe.enqueue(p.getLeft());

            if (p.getRight() != null)
                fringe.enqueue(p.getRight());
        }

        return list;
    }

    public static <E> Iterable<LinkedBinaryTreeNode<E>> inOrder(LinkedBinaryTreeNode<E> root) {
        ArrayList<LinkedBinaryTreeNode<E>> list = new ArrayList<>();
        if (root != null)
            inOrderSubTree(root, list);
        return list;
    }

    private static <E> void inOrderSubTree(LinkedBinaryTreeNode<E> node, ArrayList<LinkedBinaryTreeNode<E>> snapshot) {
        if (node.getLeft() != null)
            inOrderSubTree(node.getLeft(), snapshot);

        snapshot.add(node);

        if (node.getRight() != null)
            inOrderSubTree(node.getRight(), snapshot);
    }
}
