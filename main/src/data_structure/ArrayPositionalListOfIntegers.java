package data_structure;

import adt.Position;
import adt.PositionalList;

public class ArrayPositionalListOfIntegers implements PositionalList<Integer> {
    private static class IndexedPosition implements Position<Integer> {
        private int index;
        private Integer element;

        public IndexedPosition(Integer element, int index) {
            this.element = element;
            this.index = index;
        }

        public void setElement(Integer element) {
            this.element = element;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public Integer getElement() throws IllegalStateException {
            return element;
        }
    }

    public static final int CAPACITY = 16;
    private IndexedPosition[] list;
    private int size = 0;

    public ArrayPositionalListOfIntegers() {
        this(CAPACITY);
    }

    public ArrayPositionalListOfIntegers(int capacity) {
        list = new IndexedPosition[capacity];
    }

    /**
     * It casts and validate a particular position and cast it to node
     */
    private IndexedPosition validate(Position<Integer> position) throws IllegalArgumentException {
        if (!(position instanceof IndexedPosition positionNode))
            throw new IllegalArgumentException("An invalid Argument");

        if (positionNode.getElement() == -1) throw new IllegalArgumentException("is no longer valid position");

        return positionNode;
    }

    private Position<Integer> getPosition(int i) {
        if (i < 0 || i >= size()) return null;

        return list[i];
    }

    private IndexedPosition setElementByPosition(IndexedPosition positionNode) {
        list[positionNode.getIndex()] = positionNode;
        size++;
        return list[positionNode.getIndex()];
    }

    private void resizeIfNecessary() {
        if (size != list.length) return;

        int capacity = size * 2;
        IndexedPosition[] temp = new IndexedPosition[capacity];
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
    public Position<Integer> first() {
        if (isEmpty()) return null;

        return getPosition(0);
    }

    @Override
    public Position<Integer> last() {
        if (isEmpty()) return null;

        return getPosition(size - 1);
    }

    @Override
    public Position<Integer> before(Position<Integer> position) throws IllegalArgumentException {
        IndexedPosition positionNode = validate(position);

        return getPosition(positionNode.getIndex() - 1);
    }

    @Override
    public Position<Integer> after(Position<Integer> position) throws IllegalArgumentException {
        IndexedPosition positionNode = validate(position);

        return getPosition(positionNode.getIndex() + 1);
    }

    @Override
    public Position<Integer> addFirst(Integer element) {
        resizeIfNecessary();
        IndexedPosition positionNode = new IndexedPosition(element, 0);
        shiftElementToRight(positionNode.getIndex());
        return setElementByPosition(positionNode);
    }

    @Override
    public Position<Integer> addLast(Integer element) {
        resizeIfNecessary();
        IndexedPosition positionNode = new IndexedPosition(element, size);
        return setElementByPosition(positionNode);
    }

    @Override
    public Position<Integer> addBefore(Position<Integer> position, Integer element) {
        IndexedPosition positionNode = validate(position);

        resizeIfNecessary();

        IndexedPosition newPositionNode = new IndexedPosition(element, positionNode.getIndex());

        shiftElementToRight(newPositionNode.getIndex());
        return setElementByPosition(newPositionNode);
    }

    @Override
    public Position<Integer> addAfter(Position<Integer> position, Integer element) {
        IndexedPosition positionNode = validate(position);

        resizeIfNecessary();

        IndexedPosition newPositionNode = new IndexedPosition(element, positionNode.getIndex() + 1);

        shiftElementToRight(newPositionNode.getIndex());
        return setElementByPosition(newPositionNode);
    }

    @Override
    public Integer set(Position<Integer> position, Integer element) throws IllegalArgumentException {
        IndexedPosition positionNode = validate(position);
        Integer temp = positionNode.getElement();
        positionNode.setElement(element);
        return temp;
    }

    @Override
    public Integer remove(Position<Integer> position) throws IllegalArgumentException {
        IndexedPosition positionNode = validate(position);
        int index = positionNode.getIndex();
        Integer element = position.getElement();

        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
            list[i].setIndex(i);
        }
        size--;

        positionNode.setElement(null); // garbage collector
        positionNode.setIndex(-1); // invalid

        return element;
    }
}
