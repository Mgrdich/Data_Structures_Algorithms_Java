package Problems;

import data_structure.LinkedBinaryTreeNode;

import java.util.Iterator;

public class BSTIterator implements Iterator<Integer> {

    private LinkedBinaryTreeNode<Integer> next;


    public BSTIterator(LinkedBinaryTreeNode<Integer> root) {
        next = min(root);
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Integer next() {
        int ret = next.getElement();
        next = inOrderAfter(next);
        return ret;
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
