package Problems;

import data_structure.LinkedBinaryTreeNode;
import util.Util;

public class InOrderAfter {
    public static void main(String[] args) {
        LinkedBinaryTreeNode<Integer> root = new LinkedBinaryTreeNode<>(1, null, null, null);
        LinkedBinaryTreeNode<Integer> left = new LinkedBinaryTreeNode<>(2, root, null, null);
        LinkedBinaryTreeNode<Integer> right = new LinkedBinaryTreeNode<>(3, root, null, null);
        root.setLeft(left);
        root.setRight(right);

        LinkedBinaryTreeNode<Integer> right_left = new LinkedBinaryTreeNode<>(6, right, null, null);
//        LinkedBinaryTreeNode<Integer> right_right = new LinkedBinaryTreeNode<>(7, right, null, null);
        right.setLeft(right_left);
//        right.setRight(right_right);

        LinkedBinaryTreeNode<Integer> right_left1 = new LinkedBinaryTreeNode<>(4, left, null, null);
        LinkedBinaryTreeNode<Integer> right_right2 = new LinkedBinaryTreeNode<>(5, left, null, null);
        left.setLeft(right_left1);
        left.setRight(right_right2);


        for (LinkedBinaryTreeNode<Integer> ele : Util.inOrder(root)) {
            System.out.print(ele.getElement() + " ");
        }
        System.out.println();
        System.out.println(inOrderAfter(right).getElement());
    }

    public static LinkedBinaryTreeNode<Integer> inOrderAfter(LinkedBinaryTreeNode<Integer> node) {

        if (node.getRight() != null) {
            return min(node.getRight());
        }

        LinkedBinaryTreeNode<Integer> parentNode = node.getParent();
        while (parentNode != null && node == parentNode.getRight()) {
            node = parentNode;
            parentNode = parentNode.getParent();
        }
        return parentNode;
    }

    public static LinkedBinaryTreeNode<Integer> min(LinkedBinaryTreeNode<Integer> node) {
        // left most leaf
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
}
