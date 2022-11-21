package Problems;

import data_structure.LinkedBinaryTreeNode;
import util.Util;

public class SwapLinkedBinaryTreeNode {
    public static void main(String[] args) {
        LinkedBinaryTreeNode<Integer> root = new LinkedBinaryTreeNode<>(1, null, null, null);
        LinkedBinaryTreeNode<Integer> left1 = new LinkedBinaryTreeNode<>(2, root, null, null);
        LinkedBinaryTreeNode<Integer> right1 = new LinkedBinaryTreeNode<>(3, root, null, null);
        root.setLeft(left1);
        root.setRight(right1);


        LinkedBinaryTreeNode<Integer> left_1_1 = new LinkedBinaryTreeNode<>(4, left1, null, null);
        LinkedBinaryTreeNode<Integer> right_1_1 = new LinkedBinaryTreeNode<>(5, left1, null, null);
        left1.setLeft(left_1_1);
        left1.setRight(right_1_1);


        for (Integer e : Util.breadthFirstTraversal(root)) {
            System.out.print(e + " ");
        }

        Util.swapLinkedBinaryNodes(root, right_1_1);

        System.out.println();

        for (Integer e : Util.breadthFirstTraversal(right_1_1)) {
            System.out.print(e + " ");
        }
    }
}
