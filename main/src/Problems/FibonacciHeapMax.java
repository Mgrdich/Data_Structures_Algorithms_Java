package Problems;

import data_structure.FibonacciHeapNode;

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
//        System.out.println(findMax(node2).getKey());


        // First Root
        FibonacciHeapNode<Integer> node23 = new FibonacciHeapNode<>(23);
        FibonacciHeapNode<Integer> node7 = new FibonacciHeapNode<>(7);
        node23.setRight(node7);
        node7.setLeft(node23);

        // 3 tree
        FibonacciHeapNode<Integer> node3 = new FibonacciHeapNode<>(3);
        FibonacciHeapNode<Integer> node18 = new FibonacciHeapNode<>(18);
        node3.setChild(node18);
        node3.setLeft(node7);


        node3.setRight(node23);//TODO delete
        node23.setRight(node3);//TODO delete

        // node 18 child
        FibonacciHeapNode<Integer> node39 = new FibonacciHeapNode<>(39);
        node18.setChild(node39);
        // single child
        node39.setLeft(node39);
        node39.setRight(node39);

        FibonacciHeapNode<Integer> node52 = new FibonacciHeapNode<>(52);
        node18.setRight(node52);
        node52.setLeft(node18);

        FibonacciHeapNode<Integer> node38 = new FibonacciHeapNode<>(38);
        node52.setRight(node38);
        node38.setLeft(node52);

        // linking children together
        node38.setRight(node18);
        node18.setLeft(node38);

        // node 38 child
        FibonacciHeapNode<Integer> node41 = new FibonacciHeapNode<>(41);
        node38.setChild(node41);
        // single child
        node41.setLeft(node41);
        node41.setRight(node41);

        System.out.println(findMax(node3).getKey());

    }

    public static <T extends Comparable<T>> FibonacciHeapNode<T> findMax(FibonacciHeapNode<T> node) {
        return findMax(node, node, null);
    }


    private static <T extends Comparable<T>> FibonacciHeapNode<T> findMax(FibonacciHeapNode<T> initialNode, FibonacciHeapNode<T> currentNode, FibonacciHeapNode<T> maxNode) {
        if (currentNode == null) return maxNode;

        if (initialNode == currentNode && maxNode != null) return maxNode;


        FibonacciHeapNode<T> tempMaxNode = maxNode == null ? currentNode : currentNode.getKey().compareTo(maxNode.getKey()) > 0 ? currentNode : maxNode;

        FibonacciHeapNode<T> maxTemp1 = findMax(initialNode, currentNode.getChild(), tempMaxNode);
        FibonacciHeapNode<T> maxTemp2 = currentNode;

        // not single and not the last element
        if (currentNode.getRight() != currentNode && currentNode.getRight().getLeft() != currentNode) {
            maxTemp2 = findMax(initialNode, currentNode.getRight(), tempMaxNode);
        }

        return maxTemp1.getKey().compareTo(maxTemp2.getKey()) > 0 ? maxTemp1 : maxTemp2;
    }
}
