package Problems;

import data_structure.LinkedBinaryTreeNode;
import util.Util;

public class AVLMedian {
    public static void main(String[] args) {
        LinkedBinaryTreeNode<Integer> root = new LinkedBinaryTreeNode<>(100, null, null, null);
        LinkedBinaryTreeNode<Integer> left = new LinkedBinaryTreeNode<>(90, root, null, null);
        LinkedBinaryTreeNode<Integer> right = new LinkedBinaryTreeNode<>(110, root, null, null);
        addSentinels(right);

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
            if (val != null) System.out.print(val+ " ");
        }
    }


    public int getMedian(LinkedBinaryTreeNode<Integer> root) {
        return 1;
    }

    private static <E> void addSentinels(LinkedBinaryTreeNode<E> node) {
        node.setLeft(new LinkedBinaryTreeNode<>(null, node, null, null));
        node.setRight(new LinkedBinaryTreeNode<>(null, node, null, null));
    }
}
