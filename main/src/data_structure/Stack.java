package data_structure;

public interface Stack<E> {
    void push(E e);

    E pop();

    E top();

    int size();

    boolean isEmpty();
}
