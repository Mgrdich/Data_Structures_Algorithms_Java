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

    private Entry<K, V> insert(Stack<Entry<K, V>> st, Entry<K, V> entry) {
        Entry<K, V> top = st.top();

        if (isEmpty() || compare(top, entry) > 0) {
            st.push(entry);
            return entry;
        }

        Entry<K, V> temp = st.pop();
        Entry<K,V> res = insert(st, entry);
        st.push(temp);

        return res;
    }
}
