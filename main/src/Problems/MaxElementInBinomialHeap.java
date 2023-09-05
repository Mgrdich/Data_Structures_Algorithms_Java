package Problems;

import data_structure.BinomialHeapNode;

public class MaxElementInBinomialHeap {
    public static void main(String[] args) {
        BinomialHeapNode<Integer> root = new BinomialHeapNode<>(1);
        BinomialHeapNode<Integer> rootRightMost_1 = new BinomialHeapNode<>(2);
        rootRightMost_1.setParent(root);
        root.setRightMostChild(rootRightMost_1);

        BinomialHeapNode<Integer> node_2 = new BinomialHeapNode<>(2);
        node_2.setParent(root);
        rootRightMost_1.setLeftSibling(node_2);

        BinomialHeapNode<Integer> node_4 = new BinomialHeapNode<>(4);
        node_2.setRightMostChild(node_4);
        node_4.setParent(node_2);

        BinomialHeapNode<Integer> node_3 = new BinomialHeapNode<>(3);
        node_3.setParent(root);
        node_2.setLeftSibling(node_3);

        BinomialHeapNode<Integer> node_16 = new BinomialHeapNode<>(16);
        node_16.setParent(node_3);
        node_3.setRightMostChild(node_16);

        BinomialHeapNode<Integer> node_11 = new BinomialHeapNode<>(11);
        node_11.setParent(node_3);
        node_16.setLeftSibling(node_11);


        BinomialHeapNode<Integer> node_12 = new BinomialHeapNode<>(12);
        node_12.setParent(node_11);
        node_11.setRightMostChild(node_12);


        System.out.println(getMaximumKey(node_12));
    }

    public static Integer getMaximumKey(BinomialHeapNode<Integer> node) {
        return getMaximumRec(node.getRightMostChild(), node).getKey();
    }

    public static BinomialHeapNode<Integer> getMaximumRec(BinomialHeapNode<Integer> node, BinomialHeapNode<Integer> maxNode) {
        // Full traversal is reached for that particular node
        if (node == null) return maxNode;

        BinomialHeapNode<Integer> tempMaxNode = node.getKey() > maxNode.getKey() ? node : maxNode;

        BinomialHeapNode<Integer> temp2 = getMaximumRec(node.getRightMostChild(), tempMaxNode);
        BinomialHeapNode<Integer> temp1 = getMaximumRec(node.getLeftSibling(), tempMaxNode);


        return temp1.getKey() > temp2.getKey() ? temp1 : temp2;
    }
}
