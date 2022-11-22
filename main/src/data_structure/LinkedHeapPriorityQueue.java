package data_structure;

import adt.Entry;
import adt.Queue;

public class LinkedHeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    private int size = 0;

    private LinkedBinaryTreeNode<Entry<K, V>> root;

    private void addSentinels(LinkedBinaryTreeNode<Entry<K, V>> node) {
        LinkedBinaryTreeNode<Entry<K, V>> sentinelLeft = new LinkedBinaryTreeNode<>(null, node, null, null);
        LinkedBinaryTreeNode<Entry<K, V>> sentinelRight = new LinkedBinaryTreeNode<>(null, node, null, null);
        node.setLeft(sentinelLeft);
        node.setRight(sentinelRight);
    }

    private LinkedBinaryTreeNode<Entry<K, V>> findLastNode(LinkedBinaryTreeNode<Entry<K, V>> node) {

        Queue<LinkedBinaryTreeNode<Entry<K, V>>> fringe = new LinkedQueue<>();
        fringe.enqueue(node);
        LinkedBinaryTreeNode<Entry<K, V>> lastNoneNull = node;

        while (!fringe.isEmpty()) {
            LinkedBinaryTreeNode<Entry<K, V>> p = fringe.dequeue();

            if (p.getLeft() != null)
                fringe.enqueue(p.getLeft());

            if (p.getRight() != null)
                fringe.enqueue(p.getRight());

            if (p.getElement() == null) {
                return lastNoneNull;
            } else {
                lastNoneNull = p;
            }
        }

        return lastNoneNull;
    }

    private LinkedBinaryTreeNode<Entry<K, V>> findMinHeapNode(LinkedBinaryTreeNode<Entry<K, V>> node) {

        Queue<LinkedBinaryTreeNode<Entry<K, V>>> fringe = new LinkedQueue<>();
        fringe.enqueue(node);
        while (!fringe.isEmpty()) {
            LinkedBinaryTreeNode<Entry<K, V>> p = fringe.dequeue();

            if (p.getLeft() != null)
                fringe.enqueue(p.getLeft());

            if (p.getRight() != null)
                fringe.enqueue(p.getRight());


            if (p.getElement() == null) {
                // first sentinel
                return p;
            }
        }
        return node;
    }

    private Entry<K, V> entrify(LinkedBinaryTreeNode<Entry<K, V>> node) {
        if (node == null || node.getElement() == null) return null;

        return node.getElement();
    }

    private void upHeap(LinkedBinaryTreeNode<Entry<K, V>> node) {
        if (node == null || node.getElement() == null) return;

        LinkedBinaryTreeNode<Entry<K, V>> p = node.getParent();
        if (p == null) return;
        if (compare(node.getElement(), p.getElement()) >= 0) return;

        swapValues(node, p);

        upHeap(p);
    }

    private void downHeap(LinkedBinaryTreeNode<Entry<K, V>> node) {
        if (node.getElement() == null || node.getLeft() == null) return;

        LinkedBinaryTreeNode<Entry<K, V>> leftElement = node.getLeft();

        if (leftElement.getElement() == null) return; //sentinel

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
        Entry<K, V> temp = root.getElement();

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
        if (isEmpty()) {
            root = new LinkedBinaryTreeNode<>(newEntry, null, null, null);
            addSentinels(root);
            size++;
            return newEntry;
        }


        LinkedBinaryTreeNode<Entry<K, V>> node = findMinHeapNode(root);
        node.setElement(newEntry);
        addSentinels(node);
        upHeap(node);
        size++;
        return newEntry;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
