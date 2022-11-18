package Problems;

import data_structure.LinkedBinaryTreeNode;

public class isSameBinaryTree {

    public boolean isSameTree(LinkedBinaryTreeNode<Integer> root, LinkedBinaryTreeNode<Integer> anotherRoot) {
        if (root == null || anotherRoot == null) {
            return root == anotherRoot;
        }

        boolean result1 = isSameTree(root.getLeft(), anotherRoot.getLeft());

        boolean result2 = isSameTree(root.getRight(), anotherRoot.getRight());

        return root.getElement() == anotherRoot.getElement() && result1 && result2;
    }
}
