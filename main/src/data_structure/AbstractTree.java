package data_structure;

import adt.Position;
import adt.Tree;

public abstract class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isRoot(Position<E> node) throws IllegalArgumentException {
        return node == root();
    }

    @Override
    public boolean isExternal(Position<E> node) throws IllegalArgumentException {
        return numChildren(node) == 0;
    }

    @Override
    public boolean isInternal(Position<E> node) throws IllegalArgumentException {
        return numChildren(node) > 0;
    }

    int depth(Position<E> node) {
        if (isRoot(node)) {
            return 0;
        }

        return 1 + depth(node);
    }

    int height(Position<E> node) {
        int h = 0;
        for (Position<E> child : children(node)) {
            h = Math.max(h, 1 + height(node));
        }
        return h;
    }
}
