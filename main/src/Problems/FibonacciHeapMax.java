package Problems;

import data_structure.FibonacciHeapNode;

import java.util.HashSet;

public class FibonacciHeapMax {
    public static void main(String[] args) {
//        FibonacciHeapNode<Integer> node5 = new FibonacciHeapNode<>(5);
//        FibonacciHeapNode<Integer> node11 = new FibonacciHeapNode<>(11);
//        node5.setRight(node11);
//        node11.setLeft(node5);
//
//
//        FibonacciHeapNode<Integer> node13 = new FibonacciHeapNode<>(13);
//        node11.setRight(node13);
//        node13.setLeft(node11);
//
//        FibonacciHeapNode<Integer> node2 = new FibonacciHeapNode<>(2);
//        node13.setRight(node2);
//        node2.setLeft(node13);
//
//        FibonacciHeapNode<Integer> node1 = new FibonacciHeapNode<>(1);
//        node2.setRight(node1);
//        node1.setLeft(node2);
//
//
//        // last child
//        node1.setRight(node5);
//        node5.setLeft(node1);
//
//        FibonacciHeapNode<Integer> node66 = new FibonacciHeapNode<>(66);
//        FibonacciHeapNode<Integer> node26 = new FibonacciHeapNode<>(86);
//        setParentChild(node66, node26);
//
//
//        System.out.println(findMax(node66).getKey());


        // First Root
        FibonacciHeapNode<Integer> node23 = new FibonacciHeapNode<>(23);
        FibonacciHeapNode<Integer> node7 = new FibonacciHeapNode<>(7);
        FibonacciHeapNode<Integer> node100 = new FibonacciHeapNode<>(100);
        setParentChild(node7, node100);
        setSibling(node23, node7);

        // 3 tree
        FibonacciHeapNode<Integer> node3 = new FibonacciHeapNode<>(3);
        setSibling(node7, node3);
        setSibling(node3, node23); // cycle

        FibonacciHeapNode<Integer> node18 = new FibonacciHeapNode<>(18);
        setParentChild(node3, node18);

        // node 18 child
        FibonacciHeapNode<Integer> node39 = new FibonacciHeapNode<>(39);
        setParentChild(node18, node39);
        // single child
        setSingleChild(node39);

        FibonacciHeapNode<Integer> node52 = new FibonacciHeapNode<>(52);
        setSibling(node18, node52);

        FibonacciHeapNode<Integer> node38 = new FibonacciHeapNode<>(38);
        setSibling(node52, node38);

        setSibling(node38, node18); // cycle
        node52.setParent(node3);
        node38.setParent(node3);

        FibonacciHeapNode<Integer> node41 = new FibonacciHeapNode<>(41);
        setParentChild(node38, node41);
        // single child
        setSingleChild(node41);

        System.out.println(findMax(node3).getKey());
        System.out.println(findMaxV2(node3).getKey());

    }


    public static <T extends Comparable<T>> FibonacciHeapNode<T> findMaxV2(FibonacciHeapNode<T> minimumNode) {
        return findMaxV2(minimumNode, minimumNode, new HashSet<>());
    }

    /**
     * this is the Hacky way with the Set to keep the visited items
     * */
    private static <T extends Comparable<T>> FibonacciHeapNode<T> findMaxV2(FibonacciHeapNode<T> currentNode, FibonacciHeapNode<T> maxNode, HashSet<FibonacciHeapNode<T>> visitedNodes) {
        if (currentNode == null) return maxNode;

        if (visitedNodes.contains(currentNode)) return maxNode;

        visitedNodes.add(currentNode);

        FibonacciHeapNode<T> tempMaxNode = maxNode == null ? currentNode : currentNode.getKey().compareTo(maxNode.getKey()) > 0 ? currentNode : maxNode;

        FibonacciHeapNode<T> maxTemp1 = findMaxV2(currentNode.getChild(), tempMaxNode, visitedNodes);
        FibonacciHeapNode<T> maxTemp2 = findMaxV2(currentNode.getRight(), tempMaxNode, visitedNodes);

        return maxTemp1.getKey().compareTo(maxTemp2.getKey()) > 0 ? maxTemp1 : maxTemp2;
    }


    /**
     * General algorithm is iterated over the root childes and for each child call the max at child function
     * to get it and compare it to the current max
     */
    public static <T extends Comparable<T>> FibonacciHeapNode<T> findMax(FibonacciHeapNode<T> node) {
        return findRootMax(node, node, null);
    }

    /**
     * Iterate over the root elements and call the maximum node at root
     */
    private static <T extends Comparable<T>> FibonacciHeapNode<T> findRootMax(FibonacciHeapNode<T> initialNode, FibonacciHeapNode<T> currentNode, FibonacciHeapNode<T> maxNode) {
        if (currentNode == null) return maxNode;
        if (initialNode == currentNode.getRight() && maxNode != null) {
            return findMaxAtNode(currentNode, currentNode, maxNode);
        }

        FibonacciHeapNode<T> tempMaxNode = maxNode == null ? currentNode : currentNode.getKey().compareTo(maxNode.getKey()) > 0 ? currentNode : maxNode;

        FibonacciHeapNode<T> treeMax = findMaxAtNode(initialNode, currentNode.getChild(), tempMaxNode);
        FibonacciHeapNode<T> rootListMax = findRootMax(initialNode, currentNode.getRight(), treeMax);

        return treeMax.getKey().compareTo(rootListMax.getKey()) > 0 ? treeMax : rootListMax;
    }

    /**
     * Iterate over all the children of a particular node , and return the maximum
     */
    private static <T extends Comparable<T>> FibonacciHeapNode<T> findMaxAtNode(FibonacciHeapNode<T> initialNode, FibonacciHeapNode<T> currentNode, FibonacciHeapNode<T> maxNode) {
        if (currentNode == null) return maxNode;

        if (initialNode == currentNode.getRight() && maxNode != null) return maxNode;


        FibonacciHeapNode<T> tempMaxNode = maxNode == null ? currentNode : currentNode.getKey().compareTo(maxNode.getKey()) > 0 ? currentNode : maxNode;

        FibonacciHeapNode<T> maxTemp1 = findMaxAtNode(initialNode, currentNode.getChild(), tempMaxNode);
        FibonacciHeapNode<T> maxTemp2 = currentNode;

        // not single and not the last element , we can know that by accessing the parent and checking if we arrive back at the first child
        // since this function is not called on the root children it will wonderfully over the children of a particular node
        boolean condition = currentNode.getParent() == null || currentNode.getParent().getChild() != currentNode.getRight();
        if (currentNode.getRight() != currentNode && condition) {
            maxTemp2 = findMaxAtNode(initialNode, currentNode.getRight(), tempMaxNode);
        }

        return maxTemp1.getKey().compareTo(maxTemp2.getKey()) > 0 ? maxTemp1 : maxTemp2;
    }

    private static <T extends Comparable<T>> void setSibling(FibonacciHeapNode<T> left, FibonacciHeapNode<T> right) {
        left.setRight(right);
        right.setLeft(left);
    }

    private static <T extends Comparable<T>> void setParentChild(FibonacciHeapNode<T> parent, FibonacciHeapNode<T> child) {
        parent.setChild(child);
        child.setParent(parent);
    }

    private static <T extends Comparable<T>> void setSingleChild(FibonacciHeapNode<T> node) {
        node.setLeft(node);
        node.setRight(node);
    }

}
