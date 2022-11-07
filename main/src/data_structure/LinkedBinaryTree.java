package data_structure;
import adt.Position;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    private LinkedBinaryTreeNode<E> root;
    private int size = 0;

    protected LinkedBinaryTreeNode<E> createNode(E element, LinkedBinaryTreeNode<E> parent, LinkedBinaryTreeNode<E> left, LinkedBinaryTreeNode<E> right) {
        return new LinkedBinaryTreeNode<>(element, parent, left, right);
    }

    protected LinkedBinaryTreeNode<E> validate(Position<E> position) throws IllegalArgumentException {
        if (!(position instanceof LinkedBinaryTreeNode<E> node))
            throw new IllegalArgumentException("Not a valid position type");

        if (node.getParent() == node)
            throw new IllegalArgumentException("position is no longer valid in the tree");

        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> left(Position<E> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<E> node = validate(position);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<E> node = validate(position);
        return node.getRight();
    }

    @Override
    public Position<E> parent(Position<E> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<E> node = validate(position);
        return node.getParent();
    }

    public Position<E> addRoot(E element) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(element, null, null, null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> position, E element) throws IllegalStateException {
        LinkedBinaryTreeNode<E> parent = validate(position);

        if (parent.getLeft() != null) throw new IllegalStateException("Node already has a left child");

        LinkedBinaryTreeNode<E> child = createNode(element, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }


    public Position<E> addRight(Position<E> position, E element) throws IllegalStateException {
        LinkedBinaryTreeNode<E> parent = validate(position);

        if (parent.getRight() != null) throw new IllegalStateException("Node already has a right child");

        LinkedBinaryTreeNode<E> child = createNode(element, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    public E set(Position<E> position, E element) throws IllegalArgumentException {
        LinkedBinaryTreeNode<E> node = validate(position);
        E temp = node.getElement();
        node.setElement(element);
        return temp;
    }

    public void attach(Position<E> position, LinkedBinaryTree<E> tree, LinkedBinaryTree<E> anotherTree) {
        LinkedBinaryTreeNode<E> node = validate(position);

        if (isInternal(node)) throw new IllegalArgumentException("Node should be a leaf");

        size += tree.size + anotherTree.size;

        if (!tree.isEmpty()) {
            tree.root.setParent(node);
            node.setLeft(tree.root);
            tree.root = null;
            tree.size = 0;
        }

        if (!anotherTree.isEmpty()) {
            anotherTree.root.setParent(node);
            node.setRight(anotherTree.root);
            anotherTree.root = null;
            anotherTree.size = 0;
        }
    }

    public E remove(Position<E> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<E> node = validate(position);

        if (numChildren(node) == 2) throw new IllegalArgumentException("Node has two children");

        LinkedBinaryTreeNode<E> child = node.getLeft() != null ? node.getLeft() : node.getRight();

        if (child != null)
            child.setParent(node.getParent());

        if (node == root) {
            root = child;
        } else {
            LinkedBinaryTreeNode<E> parentNode = node.getParent();

            // determine which child is the deleted node and setting it.
            if (node == parentNode.getLeft())
                parentNode.setLeft(child);
            else
                parentNode.setRight(child);
        }

        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setRight(null);
        node.setLeft(null);
        node.setParent(node); // invalidate
        return temp;
    }
}
