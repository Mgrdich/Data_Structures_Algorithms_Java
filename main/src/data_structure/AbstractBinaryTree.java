package data_structure;

import adt.BinaryTree;
import adt.List;
import adt.Position;

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
}
