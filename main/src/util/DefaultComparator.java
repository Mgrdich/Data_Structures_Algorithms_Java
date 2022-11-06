package util;

import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E> {

    @Override
    @SuppressWarnings("all")
    public int compare(E o1, E o2) throws ClassCastException {
        return (((Comparable<E>) o1).compareTo(o2));
    }
}
