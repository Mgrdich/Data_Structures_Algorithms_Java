package data_structure;

public class BinomialHeapNode<T> {
    private BinomialHeapNode<T> rightMostChild;
    private BinomialHeapNode<T> parent;
    private BinomialHeapNode<T> leftSibling;
    public T key;

    public BinomialHeapNode(BinomialHeapNode<T> parent, BinomialHeapNode<T> rightMostChild, BinomialHeapNode<T> leftSibling) {
        this.parent = parent;
        this.rightMostChild = rightMostChild;
        this.leftSibling = leftSibling;
    }

    public BinomialHeapNode(BinomialHeapNode<T> parent) {
        this.parent = parent;
    }

    public BinomialHeapNode(T value) {
        this.key = value;
    }

    public BinomialHeapNode() {}

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public void setParent(BinomialHeapNode<T> parent) {
        this.parent = parent;
    }

    public BinomialHeapNode<T> getParent() {
        return this.parent;
    }


    public void setRightMostChild(BinomialHeapNode<T> rightMostChild) {
        this.rightMostChild = rightMostChild;
    }

    public BinomialHeapNode<T> getRightMostChild() {
        return this.rightMostChild;
    }


    public void setLeftSibling(BinomialHeapNode<T> leftSibling) {
        this.leftSibling = leftSibling;
    }

    public BinomialHeapNode<T> getLeftSibling() {
        return this.leftSibling;
    }
}
