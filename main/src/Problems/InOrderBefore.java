package Problems;

import data_structure.LinkedBinaryTreeNode;
import util.Util;

public class InOrderBefore {
    public static void main(String[] args) {
        LinkedBinaryTreeNode<Integer> root = new LinkedBinaryTreeNode<>(1, null, null, null);
        LinkedBinaryTreeNode<Integer> left = new LinkedBinaryTreeNode<>(2, root, null, null);
        LinkedBinaryTreeNode<Integer> right = new LinkedBinaryTreeNode<>(3, root, null, null);
        root.setLeft(left);
        root.setRight(right);

        LinkedBinaryTreeNode<Integer> right_left = new LinkedBinaryTreeNode<>(6, right, null, null);
        LinkedBinaryTreeNode<Integer> right_right = new LinkedBinaryTreeNode<>(7, right, null, null);
        right.setLeft(right_left);
        right.setRight(right_right);

        LinkedBinaryTreeNode<Integer> right_left1 = new LinkedBinaryTreeNode<>(4, left, null, null);
        LinkedBinaryTreeNode<Integer> right_right2 = new LinkedBinaryTreeNode<>(5, left, null, null);
        left.setLeft(right_left1);
        left.setRight(right_right2);


        for (LinkedBinaryTreeNode<Integer> ele : Util.inOrder(root)) {
            System.out.print(ele.getElement() + " ");
        }
        System.out.println();
        System.out.println(inOrderBefore(right_left1).getElement());
    }

    public static LinkedBinaryTreeNode<Integer> inOrderBefore(LinkedBinaryTreeNode<Integer> node) {

        if (node.getLeft() != null) {
            return max(node.getLeft());
        }

        LinkedBinaryTreeNode<Integer> currentNode = node.getParent();
        while (currentNode != null && node == currentNode.getLeft()) {
            node = currentNode;
            currentNode = currentNode.getParent();
        }
        return currentNode;
    }

    public static LinkedBinaryTreeNode<Integer> max(LinkedBinaryTreeNode<Integer> node) {
        // left most leaf
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }
}
