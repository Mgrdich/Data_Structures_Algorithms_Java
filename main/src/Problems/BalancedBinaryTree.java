package Problems;

import adt.Position;
import data_structure.LinkedBinaryTree;
import data_structure.LinkedBinaryTreeNode;

/**
 * Height balanced , if the depth of the two subtrees of every node never differs by one
 */
public class BalancedBinaryTree {

    public static boolean isBalanced(LinkedBinaryTreeNode<Integer> root) {
        return balancedHeight(root) != -1;
    }

    private static int balancedHeight(LinkedBinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int leftSubTreeHeight = balancedHeight(root.getLeft());
        if (leftSubTreeHeight == -1) return -1;

        int rightSubTreeHeight = balancedHeight(root.getRight());
        if (rightSubTreeHeight == -1) return -1;

        // checking the difference of left and right subtree for current node
        if (Math.abs(leftSubTreeHeight - rightSubTreeHeight) > 1) {
            return -1;
        }

        // if it is balanced then return the height
        return (Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1);
    }

    public static boolean isBalanced(LinkedBinaryTree<Integer> tree) {
        return balancedHeight(tree, tree.root()) != -1;
    }

    private static int balancedHeight(LinkedBinaryTree<Integer> tree, Position<Integer> root) {
        if (root.getElement() == null) {
            return 0;
        }

        int leftSubTreeHeight = balancedHeight(tree, tree.left(root));
        if (leftSubTreeHeight == -1) return -1;

        int rightSubTreeHeight = balancedHeight(tree, tree.right(root));
        if (rightSubTreeHeight == -1) return -1;


        if (Math.abs(leftSubTreeHeight - rightSubTreeHeight) > 1) {
            return -1;
        }

        // if it is balanced then return the height
        return (Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1);
    }
}
