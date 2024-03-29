package data_structure;

public class FibonacciHeapNode<T> {
    private int degree = 0;
    private FibonacciHeapNode<T> right;
    private FibonacciHeapNode<T> left;
    private FibonacciHeapNode<T> parent;
    private FibonacciHeapNode<T> child;
    private T key;

    public FibonacciHeapNode(T key) {
        this.key = key;
    }

    public int getDegree() {
        return this.degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public FibonacciHeapNode<T> getParent() {
        return this.parent;
    }

    public void setParent(FibonacciHeapNode<T> parent) {
        this.parent = parent;
    }

    public FibonacciHeapNode<T> getLeft() {
        return this.left;
    }

    public void setLeft(FibonacciHeapNode<T> left) {
        this.left = left;
    }

    public FibonacciHeapNode<T> getRight() {
        return this.right;
    }

    public void setRight(FibonacciHeapNode<T> right) {
        this.right = right;
    }

    public FibonacciHeapNode<T> getChild() {
        return this.child;
    }

    public void setChild(FibonacciHeapNode<T> child) {
        this.child = child;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
