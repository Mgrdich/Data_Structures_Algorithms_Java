package adt;

public interface Set <K> extends Iterable<K> {
    int size();

    boolean isEmpty();

    boolean contains(K key);

    void add(K key);

    K remove(K key);
}
