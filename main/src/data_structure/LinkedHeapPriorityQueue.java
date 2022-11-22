package data_structure;

import adt.Entry;
import adt.Queue;

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

    private LinkedBinaryTreeNode<Entry<K, V>> findLastNode(LinkedBinaryTreeNode<Entry<K, V>> node) {

        Queue<LinkedBinaryTreeNode<Entry<K, V>>> fringe = new LinkedQueue<>();
        fringe.enqueue(node);
        while (!fringe.isEmpty()) {
            LinkedBinaryTreeNode<Entry<K, V>> p = fringe.dequeue();

            if (p.getLeft() != null)
                fringe.enqueue(p.getLeft());

            if (p.getRight() != null)
                fringe.enqueue(p.getRight());


            if (p.getRight() == null && p.getLeft() == null && fringe.size() == 1) {
                return fringe.dequeue();
            }
        }
        return node;
    }

    private Entry<K, V> entrify(LinkedBinaryTreeNode<Entry<K, V>> node) {
        if (node == null) return null;

        return node.getElement();
    }

    private void upHeap(LinkedBinaryTreeNode<Entry<K, V>> node) {
        if (node == null) return;

        LinkedBinaryTreeNode<Entry<K, V>> p = node.getParent();
        if (compare(node.getElement(), p.getElement()) >= 0) return;

        swapValues(node, p);

        upHeap(p);
    }

    private void downHeap(LinkedBinaryTreeNode<Entry<K, V>> node) {
        if (node.getLeft() == null) return;

        LinkedBinaryTreeNode<Entry<K, V>> leftElement = node.getLeft();
        LinkedBinaryTreeNode<Entry<K, V>> smallestNode = leftElement;

        if (node.getRight() != null) {
            LinkedBinaryTreeNode<Entry<K, V>> rightElement = node.getRight();
            if (compare(leftElement.getElement(), rightElement.getElement()) > 0) {
                smallestNode = rightElement;
            }
        }

        if (compare(smallestNode.getElement(), node.getElement()) >= 0) return;

        swapValues(node, smallestNode);
        downHeap(smallestNode);
    }

    private void swapValues(LinkedBinaryTreeNode<Entry<K, V>> node, LinkedBinaryTreeNode<Entry<K, V>> anotherNode) {
        Entry<K, V> temp = node.getElement();
        node.setElement(anotherNode.getElement());
        anotherNode.setElement(temp);
    }

    private void remove(LinkedBinaryTreeNode<Entry<K, V>> node) {
        node.setElement(null);
        node.setParent(null);
        node.setRight(null);
        node.setLeft(null);
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
        if (isEmpty()) return null;

        LinkedBinaryTreeNode<Entry<K, V>> lastHeapNode = findLastNode(root);
        Entry<K, V> temp = lastHeapNode.getElement();
        swapValues(root, lastHeapNode);

        remove(lastHeapNode); // now it has the root element
        size--;

        if (size > 1) {
            downHeap(root);
        } else {
            root = null;
        }

        return temp;
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
