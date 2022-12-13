package Problems;

import data_structure.LinkedBinaryTreeNode;

public class MaximumMinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        LinkedBinaryTreeNode<Integer> root = new LinkedBinaryTreeNode<>(100, null, null, null);
        LinkedBinaryTreeNode<Integer> left = new LinkedBinaryTreeNode<>(90, root, null, null);
        LinkedBinaryTreeNode<Integer> right = new LinkedBinaryTreeNode<>(110, root, null, null);
        root.setLeft(left);
        root.setRight(right);

        LinkedBinaryTreeNode<Integer> right_left = new LinkedBinaryTreeNode<>(105, right, null, null);
        LinkedBinaryTreeNode<Integer> right_right = new LinkedBinaryTreeNode<>(120, right, null, null);
        right.setLeft(right_left);
        right.setRight(right_right);

        System.out.println(minDepth(root));
    }

    public static int minDepth(LinkedBinaryTreeNode<Integer> root) {
        if (root == null) return 0;

        int d1 = minDepth(root.getLeft());

        int d2 = 1 + minDepth(root.getRight());

        if (d1 == 1) return d2;

        if (d2 == 1) return d1;

        return Math.min(d1, d2);
    }

    public int maxDepth(LinkedBinaryTreeNode<Integer> root) {
        if (root == null) return 0;
        int d = 0;

        if (root.getLeft() != null) {
            d = Math.max(d, 1 + minDepth(root.getLeft()));
        }

        if (root.getRight() != null) {
            d = Math.max(d, 1 + minDepth(root.getRight()));
        }

        return d;
    }
}
