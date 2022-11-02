package data_structure;

import adt.Position;
import adt.PositionalList;


import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO should be tested
public class ArrayPositionalList<E> implements PositionalList<E> {
    private static class IndexedPosition<E> implements Position<E> {
        private int index;
        private E element;

        public IndexedPosition(E element, int index) {
            this.element = element;
            this.index = index;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }
    }

    public static final int CAPACITY = 16;
    private IndexedPosition<E>[] list;
    private int size = 0;

    public ArrayPositionalList() {
        this(CAPACITY);
    }

    public ArrayPositionalList(int capacity) {
        list = new IndexedPosition[capacity];
    }

    /**
     * It casts and validate a particular position and cast it to node
     */
    private IndexedPosition<E> validate(Position<E> position) throws IllegalArgumentException {
        if (!(position instanceof IndexedPosition<E> positionNode))
            throw new IllegalArgumentException("An invalid Argument");

        if (positionNode.getIndex() == -1) throw new IllegalArgumentException("is no longer valid position");

        return positionNode;
    }

    private Position<E> getPosition(int i) {
        if (i < 0 || i >= size()) return null;

        return list[i];
    }

    private IndexedPosition<E> setElementByPosition(IndexedPosition<E> positionNode) {
        list[positionNode.getIndex()] = positionNode;
        size++;
        return list[positionNode.getIndex()];
    }

    private void resizeIfNecessary() {
        if (size != list.length) return;

        int capacity = size * 2;
        IndexedPosition<E>[] temp = new IndexedPosition[capacity];
        System.arraycopy(list, 0, temp, 0, size);
        list = temp;
    }

    private void shiftElementToRight(int start) {
        for (int i = size - 1; i >= start; i--) {
            list[i + 1] = list[i];
            list[i + 1].setIndex(i + 1); // update the reference index
        }

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> first() {
        if (isEmpty()) return null;

        return getPosition(0);
    }

    @Override
    public Position<E> last() {
        if (isEmpty()) return null;

        return getPosition(size - 1);
    }

    @Override
    public Position<E> before(Position<E> position) throws IllegalArgumentException {
        IndexedPosition<E> positionNode = validate(position);

        return getPosition(positionNode.getIndex() - 1);
    }

    @Override
    public Position<E> after(Position<E> position) throws IllegalArgumentException {
        IndexedPosition<E> positionNode = validate(position);

        return getPosition(positionNode.getIndex() + 1);
    }


    @Override
    public Position<E> addFirst(E element) {
        resizeIfNecessary();
        IndexedPosition<E> positionNode = new IndexedPosition<>(element, 0);
        shiftElementToRight(positionNode.getIndex());
        return setElementByPosition(positionNode);
    }

    @Override
    public Position<E> addLast(E element) {
        resizeIfNecessary();
        IndexedPosition<E> positionNode = new IndexedPosition<>(element, size);
        return setElementByPosition(positionNode);
    }

    @Override
    public Position<E> addBefore(Position<E> position, E element) {
        IndexedPosition<E> positionNode = validate(position);

        resizeIfNecessary();

        IndexedPosition<E> newPositionNode = new IndexedPosition<>(element, positionNode.getIndex());

        shiftElementToRight(newPositionNode.getIndex());
        return setElementByPosition(newPositionNode);
    }

    @Override
    public Position<E> addAfter(Position<E> position, E element) {
        IndexedPosition<E> positionNode = validate(position);

        resizeIfNecessary();

        IndexedPosition<E> newPositionNode = new IndexedPosition<>(element, positionNode.getIndex() + 1);

        shiftElementToRight(newPositionNode.getIndex());
        return setElementByPosition(newPositionNode);
    }

    @Override
    public E set(Position<E> position, E element) throws IllegalArgumentException {
        IndexedPosition<E> positionNode = validate(position);
        E temp = positionNode.getElement();
        positionNode.setElement(element);
        return temp;
    }

    @Override
    public E remove(Position<E> position) throws IllegalArgumentException {
        IndexedPosition<E> positionNode = validate(position);
        int index = positionNode.getIndex();
        E element = position.getElement();

        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
            list[i].setIndex(i);
        }
        if (index == size - 1) {
            list[index] = null; // garbage collector
        }

        size--;

        positionNode.setElement(null); // garbage collector
        positionNode.setIndex(-1); // invalid

        return element;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("(");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(list[i].getElement());
            if (i != size - 1) {
                stringBuilder.append(", ");
            }

        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    private class PositionIndexIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first();
        private Position<E> recent = null;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            ArrayPositionalList.this.remove(recent);
            recent = null;
        }
    }

    private class PositionIndexIterable implements Iterable<Position<E>> {
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIndexIterator();
        }
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> positionIterator = new PositionIndexIterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterable<Position<E>> positions() {
        return new PositionIndexIterable();
    }
}
