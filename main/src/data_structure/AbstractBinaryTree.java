package data_structure;

import adt.BinaryTree;
import adt.List;
import adt.Position;
import adt.Queue;

import java.util.Iterator;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    @Override
    public Position<E> sibling(Position<E> node) throws IllegalArgumentException {
        Position<E> parent = parent(node);
        if (parent == null) return null;

        if (node == left(parent))
            return right(parent);

        return left(parent);
    }

    @Override
    public int numChildren(Position<E> parent) throws IllegalArgumentException {
        int count = 0;

        if (left(parent) != null) count++;

        if (right(parent) != null) count++;

        return count;
    }


    @Override
    public Iterable<Position<E>> children(Position<E> node) {
        List<Position<E>> snapshot = new ArrayList<>(2);
        if (left(node) != null)
            snapshot.add(left(node));
        if (right(node) != null)
            snapshot.add(right(node));

        return snapshot;
    }


    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> positionIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }

        @Override
        public void remove() {
            positionIterator.remove();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override
    public Iterable<Position<E>> positions() {
        return inOrder();
    }

    private void preOrderSubTree(Position<E> position, List<Position<E>> snapshot) {
        snapshot.add(position);
        for (Position<E> childPosition : children(position)) {
            preOrderSubTree(childPosition, snapshot);
        }
    }

    public Iterable<Position<E>> preOrder() {
        List<Position<E>> list = new ArrayList<>();
        if (!isEmpty())
            preOrderSubTree(root(), list);
        return list;
    }

    private void postOrderSubTree(Position<E> position, List<Position<E>> snapshot) {
        for (Position<E> childPosition : children(position)) {
            preOrderSubTree(childPosition, snapshot);
        }
        snapshot.add(position);
    }

    public Iterable<Position<E>> postOrder() {
        List<Position<E>> list = new ArrayList<>();
        if (!isEmpty())
            postOrderSubTree(root(), list);
        return list;
    }

    private void inOrderSubTree(Position<E> position, List<Position<E>> snapshot) {
        if (left(position) != null)
            inOrderSubTree(left(position), snapshot);

        snapshot.add(position);

        if (right(position) != null)
            inOrderSubTree(right(position), snapshot);
    }

    public Iterable<Position<E>> inOrder() {
        List<Position<E>> list = new ArrayList<>();
        if (!isEmpty())
            inOrderSubTree(root(), list);
        return list;
    }

    public Iterable<Position<E>> breadthFirst() {
        List<Position<E>> list = new ArrayList<>();
        if (isEmpty())
            return list;
        Queue<Position<E>> fringe = new LinkedQueue<>();
        fringe.enqueue(root());
        while (!fringe.isEmpty()) {
            Position<E> p = fringe.dequeue();
            list.add(p);
            for (Position<E> c : children(p)) {
                fringe.enqueue(c);
            }
        }
        return list;
    }
}
