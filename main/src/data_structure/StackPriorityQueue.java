package data_structure;

import adt.Entry;
import adt.Stack;

public class StackPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    Stack<Entry<K, V>> stack = new LinkedStack<>();

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Entry<K, V> min() {
        if (isEmpty()) return null;
        return stack.top();
    }

    @Override
    public Entry<K, V> removeMin() {
        if (isEmpty()) return null;

        return stack.pop();
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        return insert(stack, new PQEntry<>(key, value));
    }

    private Entry<K, V> insert(Stack<Entry<K, V>> stack, Entry<K, V> entry) {
        Entry<K, V> top = stack.pop();
        if (isEmpty() || compare(top, entry) > 0) {
            stack.push(entry);
            return entry;
        }

        Entry<K,V> res = insert(stack, entry);
        stack.push(top);

        return res;
    }
}
