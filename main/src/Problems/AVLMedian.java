package Problems;

import data_structure.LinkedBinaryTreeNode;
import util.Util;

public class AVLMedian {
    public static void main(String[] args) {
        LinkedBinaryTreeNode<Integer> root = new LinkedBinaryTreeNode<>(100, null, null, null);
        LinkedBinaryTreeNode<Integer> left = new LinkedBinaryTreeNode<>(90, root, null, null);
        LinkedBinaryTreeNode<Integer> right = new LinkedBinaryTreeNode<>(110, root, null, null);
        LinkedBinaryTreeNode<Integer> right_left = new LinkedBinaryTreeNode<>(105, right, null, null);
        LinkedBinaryTreeNode<Integer> right_right = new LinkedBinaryTreeNode<>(120, right, null, null);
        right.setLeft(right_left);
//        right.setRight(right_right);
        addRightSentinels(right);

        addSentinels(right_left);
        addSentinels(right_right);

        root.setLeft(left);
        root.setRight(right);

        LinkedBinaryTreeNode<Integer> left_left = new LinkedBinaryTreeNode<>(80, left, null, null);
        LinkedBinaryTreeNode<Integer> left_right = new LinkedBinaryTreeNode<>(95, left, null, null);
        left.setLeft(left_left);
        left.setRight(left_right);

        addSentinels(left_left);
        addSentinels(left_right);

        for (LinkedBinaryTreeNode<Integer> ele : Util.inOrder(root)) {
            Integer val = ele.getElement();
            if (val != null) System.out.print(val + " ");
        }

        System.out.println();
//        System.out.println(left_left.getElement() + " " + inOrderAfter(left_left).getElement());

        System.out.println(getMedianMemory(root));
    }

    interface SizeAble<E> {
        int size(LinkedBinaryTreeNode<E> root);
    }

    static class MemorySize<E> implements SizeAble<E> {
        /**
         * In the case of an AVLTree it will take
         * <p>
         * O(log(n)) space
         * O(log(n)) time
         * <p>
         * because the height of AVL is at most log(n)
         */
        @Override
        public int size(LinkedBinaryTreeNode<E> node) {
            if (isExternal(node)) return 0;

            return 1 + size(node.getLeft()) + size(node.getRight());
        }
    }

    static class SizeConstant<E> implements SizeAble<E> {
        /**
         * In the case of an AVLTree it will take
         * <p>
         * O(1) space lets assume TODO not implemented
         */
        @Override
        public int size(LinkedBinaryTreeNode<E> node) {
            if (isExternal(node)) return 0;

            return 1 + size(node.getLeft()) + size(node.getRight());
        }
    }

    /**
     * space O(log(n))
     * time O(log(n))
     * */
    public static int getMedianMemory(LinkedBinaryTreeNode<Integer> root) {
        SizeAble<Integer> sizeAble = new MemorySize<>();

        int size = sizeAble.size(root); // log(n)

        if (size == 0) {
            throw new IllegalStateException("empty tree.");
        }

        // anyone case that
        return findNodeWithIndex(root, sizeAble, size / 2).getElement();
    }

    public static int getMedianConstant(LinkedBinaryTreeNode<Integer> root) {
        SizeAble<Integer> sizeAble = new SizeConstant<>();

        int size = sizeAble.size(root); // O(1)

        if (size == 0) {
            throw new IllegalStateException("empty tree.");
        }

        // anyone cases that
        return findNodeWithIndex(root, sizeAble, size / 2).getElement();
    }

    private static <E> void addSentinels(LinkedBinaryTreeNode<E> node) {
        addLeftSentinels(node);
        addRightSentinels(node);
    }

    private static <E> void addRightSentinels(LinkedBinaryTreeNode<E> node) {
        node.setRight(new LinkedBinaryTreeNode<>(null, node, null, null));
    }

    private static <E> void addLeftSentinels(LinkedBinaryTreeNode<E> node) {
        node.setLeft(new LinkedBinaryTreeNode<>(null, node, null, null));
    }

    private static <E> boolean isExternal(LinkedBinaryTreeNode<E> node) {
        return node.getRight() == null && node.getLeft() == null;
    }

    /**
     * this will depend on mainly on the sizable main function
     * otherwise the loop itself will run log(n) times
     * <p>
     * hence the complexity log(n) * complexity(size(x))
     */
    private static <E> LinkedBinaryTreeNode<E> findNodeWithIndex(LinkedBinaryTreeNode<E> root, SizeAble<E> sizeable, int index) {
        LinkedBinaryTreeNode<E> current = root;

        while (true) {
            int leftSize = sizeable.size((current.getLeft()));

            // the check
            if (index == leftSize) {
                return current;
            }

            // chose the place to make it as a root and search there
            if (index <= leftSize) {
                current = current.getLeft();
            } else {
                index -= (leftSize + 1); // add root
                current = current.getRight();
            }
        }
    }
}
