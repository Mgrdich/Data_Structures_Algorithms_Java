package Problems;

import data_structure.LinkedBinaryTreeNode;

public class InvertBinaryTree {
    public static void main(String[] args) {
        LinkedBinaryTreeNode<Integer> root = new LinkedBinaryTreeNode<>(1, null, null, null);
        LinkedBinaryTreeNode<Integer> left1 = new LinkedBinaryTreeNode<>(2, root, null, null);
        LinkedBinaryTreeNode<Integer> right1 = new LinkedBinaryTreeNode<>(3, root, null, null);
        root.setLeft(left1);
        root.setRight(right1);

        LinkedBinaryTreeNode<Integer> left1_1 = new LinkedBinaryTreeNode<>(4, left1, null, null);
        left1.setLeft(left1_1);

        System.out.println(invertTree(root));
    }

    public static LinkedBinaryTreeNode<Integer> invertTree(LinkedBinaryTreeNode<Integer> root) {
        if (root == null) return null;

        return change(root, root.getLeft(), root.getRight());
    }

    private static LinkedBinaryTreeNode<Integer> change(LinkedBinaryTreeNode<Integer> node, LinkedBinaryTreeNode<Integer> left, LinkedBinaryTreeNode<Integer> right) {
        if (node == null) return null;

        if (left != null)
            change(left, left.getLeft(), left.getRight());

        if (right != null)
            change(right, right.getLeft(), right.getRight());

        swapNodes(node, left, right);

        return node;
    }


    private static void swapNodes(LinkedBinaryTreeNode<Integer> parent, LinkedBinaryTreeNode<Integer> node, LinkedBinaryTreeNode<Integer> anotherNode) {
        parent.setLeft(anotherNode);
        parent.setRight(node);
    }
}
