package data_structure;

import adt.Entry;
import util.Util;

public class LinkedHeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    private int size = 0;

    private LinkedBinaryTreeNode<Entry<K, V>> root;

    private void findAndInsertNode(LinkedBinaryTreeNode<Entry<K, V>> node, LinkedBinaryTreeNode<Entry<K, V>> entry) {
        if (node.getLeft() == null) {
            // insert and stop
            node.setLeft(entry);
            return;
        }
        if (node.getRight() == null) {
            // insert and stop
            node.setRight(entry);
            return;
        }

        findAndInsertNode(node.getLeft(), entry);
        findAndInsertNode(node.getRight(), entry);
    }

    private Entry<K, V> entrify(LinkedBinaryTreeNode<Entry<K, V>> node) {
        if (node == null) return null;

        return node.getElement();
    }

    private void upHeap(LinkedBinaryTreeNode<Entry<K, V>> node) {
        if (node == null) return;

        LinkedBinaryTreeNode<Entry<K, V>> p = node.getParent();
        if (compare(node.getElement(), p.getElement()) >= 0) return;


    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Entry<K, V> min() {
        return entrify(root);
    }

    @Override
    public Entry<K, V> removeMin() {
        size--;
        return null;
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        Entry<K, V> newEntry = new PQEntry<>(key, value);
        size++;
        if (isEmpty()) {
            root = new LinkedBinaryTreeNode<>(newEntry, null, null, null);
            return newEntry;
        }


        LinkedBinaryTreeNode<Entry<K, V>> node = new LinkedBinaryTreeNode<>(newEntry, null, null, null);
        findAndInsertNode(root, node);
        upHeap(node);
        return newEntry;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
