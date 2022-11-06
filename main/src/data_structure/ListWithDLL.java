package data_structure;

import adt.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ListWithDll is the data_structure that implements List with DoublyLinkedList underneath
 * <p>
 * the main idea here that implement all the functions is the use of the recursion
 * in order to keep the memory consumption constant , even though the time consumption
 * asymptotically is the same for all the functions with a copy version , the recursion will act slower
 * all the respective analysis of each method is written on the methods header
 * <p>
 * Since I did not want to touch the doublyLinkedList structure , that is why I came up with this recursive solution
 * if I had the iterative protocol in the DoublyLinkedList , then I would have used the adapter design pattern which is
 * a piece of cake
 */
public class ListWithDLL<E> implements List<E> {
    private final DoublyLinkedList<E> list;

    public ListWithDLL() {
        list = new DoublyLinkedList<>();
    }

    /**
     * Time-complexity: O(1) since it calls DoublyLinkedList isEmpty and that is in itself O(1)
     * space-complexity: ^^ same argument
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Time-complexity: O(1) since it calls DoublyLinkedList size and that is in itself O(1)
     * space-complexity: ^^ same argument
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Time-complexity: it uses two functions indexChecker O(1) get O(n) -> O(n)
     * space-complexity: uses references no new array creation
     */
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        indexChecker(i, size());// O(1)
        return get(list, i, 0);// O(n)
    }

    /**
     * Time-complexity: it uses two functions indexChecker O(1) set O(n) -> O(n)
     * space-complexity: uses references no new array creation
     */
    @Override
    public E set(int i, E element) throws IndexOutOfBoundsException {
        indexChecker(i, size());
        return set(list, element, i, 0);
    }

    /**
     * Time-complexity: it uses two functions indexChecker O(1) get O(n) -> O(n)
     * space-complexity: uses references no new array creation
     */
    @Override
    public void add(int i, E element) throws IndexOutOfBoundsException {
        indexChecker(i, size() + 1);
        add(list, element, i, 0);
    }

    @Override
    public void add(E element) {
        add(size(), element);
    }

    /**
     * Time-complexity: it uses two functions indexChecker O(1) get O(n) -> O(n)
     * space-complexity: uses references no new array creation
     */
    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        indexChecker(i, size());
        return remove(list, i, 0);
    }

    @Override
    public E remove() throws IndexOutOfBoundsException {
        return remove(size() - 1);
    }

    /**
     * Time-complexity: it calls toString of the DoublyLinkedList O(n)
     * space-complexity: ^^same argument
     */
    @Override
    public String toString() {
        return list.toString();
    }

    /**
     * Time-complexity: O(1) all the operation in it are O(1) assuming new IndexOutOfBoundsException is O(1)
     * space-complexity: O(1) no new array creation
     */
    private void indexChecker(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) throw new IndexOutOfBoundsException("Out of bounds");
    }

    /**
     * Time-complexity: all the calls in it are O(1) and this recurssive function is being called at worst n times
     * hence the algorithmic complexity is O(n)
     * space-complexity: O(1) no new array creation
     */
    private E get(DoublyLinkedList<E> doublyLinkedList, int i, int count) {
        if (i == count) return doublyLinkedList.first(); // O(1) from DoublyLinkedList

        E first = doublyLinkedList.removeFirst();// O(1) from DoublyLinkedList
        E res = get(doublyLinkedList, i, ++count);
        doublyLinkedList.addFirst(first); // O(1) from DoublyLinkedList

        return res;
    }

    /**
     * Time-complexity: all the calls in it are O(1) and this recursive function is being called at worst n times
     * hence the algorithmic complexity is O(n)
     * space-complexity: O(1) no new array creation
     */
    private void add(DoublyLinkedList<E> doublyLinkedList, E element, int i, int count) {
        if (i == count) {
            doublyLinkedList.addFirst(element); // O(1) from DoublyLinkedList
            return;
        }

        E first = doublyLinkedList.removeFirst(); // O(1) from DoublyLinkedList
        add(doublyLinkedList, element, i, ++count);
        doublyLinkedList.addFirst(first); // O(1) from DoublyLinkedList
    }

    /**
     * Time-complexity: all the calls in it are O(1) and this recursive function is being called at worst n times
     * hence the algorithmic complexity is O(n)
     * space-complexity: O(1) no new array creation
     */
    private E set(DoublyLinkedList<E> doublyLinkedList, E element, int i, int count) {
        E first = doublyLinkedList.removeFirst();

        if (i == count) {
            doublyLinkedList.addFirst(element);
            return first;
        }

        E returnable = set(doublyLinkedList, element, i, ++count);
        doublyLinkedList.addFirst(first);

        return returnable;
    }

    /**
     * Time-complexity: all the calls in it are O(1) and this recursive function is being called at worst n times
     * hence the algorithmic complexity is O(n)
     * space-complexity: O(1) no new array creation
     */
    private E remove(DoublyLinkedList<E> doublyLinkedList, int i, int count) {
        E first = doublyLinkedList.removeFirst();

        if (i == count) {
            return first;
        }

        E returnable = remove(doublyLinkedList, i, ++count);
        doublyLinkedList.addFirst(first);

        return returnable;
    }


    /**
     * Time-complexity: O(1) since the inner constructor complexity is O(1) uses the default one
     * space-complexity: O(1) no new array creation
     */
    @Override
    public Iterator<E> iterator() {
        return new ListDllIterator();
    }

    private class ListDllIterator implements Iterator<E> {
        private int j = 0;
        private boolean removable = false;

        private final int itSize = size() / 2;

        /**
         * Time-complexity: O(1) obviously
         * space-complexity: O(1) no new array creation
         */
        @Override
        public boolean hasNext() {
            return j < itSize;
        }

        /**
         * Time-complexity: O(n) since get() is O(n)
         * and we assumed that constructor NoSuchElementException("No next element") is O(1)
         * space-complexity: O(1) no new array creation
         */
        @Override
        public E next() throws NoSuchElementException {
            if (j == itSize) throw new NoSuchElementException("No next element");
            removable = true;
            return ListWithDLL.this.get(j++);
        }

        /**
         * Time-complexity: O(n) since remove() is O(n)
         * and we assumed that constructor NoSuchElementException("No next element") is O(1)
         * space-complexity: O(1) no new array creation
         */
        public void remove() throws IllegalStateException {
            if (!removable) throw new IllegalStateException("nothing to remove");
            ListWithDLL.this.remove(j - 1);
            j--;
            removable = false;
        }
    }
}
