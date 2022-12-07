package data_structure;

import adt.Entry;
import adt.Position;

public class AVLTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {
    protected static class AVLNode<E> extends LinkedBinaryTreeNode<E> {
        protected int height = 0;

        public AVLNode(E element, LinkedBinaryTreeNode<E> parent, LinkedBinaryTreeNode<E> left, LinkedBinaryTreeNode<E> right) {
            super(element, parent, left, right);
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public int getHeight(Position<Entry<K, V>> position) {
        return ((AVLNode<Entry<K, V>>) position).getHeight();
    }

    public void setHeight(Position<Entry<K, V>> position, int height) {
        ((AVLNode<Entry<K, V>>) position).setHeight(height);
    }


    protected LinkedBinaryTreeNode<Entry<K, V>> createNode(Entry<K, V> element, LinkedBinaryTreeNode<Entry<K, V>> parent, LinkedBinaryTreeNode<Entry<K, V>> left, LinkedBinaryTreeNode<Entry<K, V>> right) {
        return new AVLNode<>(element, parent, left, right);
    }

    private void relink(LinkedBinaryTreeNode<Entry<K, V>> parent, LinkedBinaryTreeNode<Entry<K, V>> child, boolean makeLeft) {
        child.setParent(parent);

        if (makeLeft) {
            parent.setLeft(child);
            return;
        }

        parent.setRight(child);
    }

    public void rotate(Position<Entry<K, V>> child) {
        LinkedBinaryTreeNode<Entry<K, V>> childNode = validate(child);

        LinkedBinaryTreeNode<Entry<K, V>> parentNode = childNode.getParent();
        LinkedBinaryTreeNode<Entry<K, V>> grandParent = parentNode.getParent();

        if (grandParent == null) {
            // root
            root = childNode;
            childNode.setParent(null);
        } else {
            // child become direct child of grandparent
            relink(grandParent, childNode, parentNode == grandParent.getLeft());
        }

        if (childNode == parentNode.getLeft()) {
            relink(parentNode, childNode.getRight(), true);
            relink(childNode, parentNode, false);
            return;
        }

        relink(parentNode, childNode.getLeft(), true);
        relink(childNode, parentNode, false);
    }


    public Position<Entry<K, V>> restructure(Position<Entry<K, V>> position) {
        Position<Entry<K, V>> parentNode = parent(position);
        Position<Entry<K, V>> grandParent = parent(parentNode);

        if ((position == right(parentNode)) && (parentNode == right(grandParent))) {
            rotate(position);
            return parentNode;
        }

        rotate(position);
        rotate(position);
        return position;
    }
}
