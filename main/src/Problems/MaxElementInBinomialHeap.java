package Problems;

import data_structure.BinomialHeapNode;

public class MaxElementInBinomialHeap {
    public static void main(String[] args) {
            BinomialHeapNode<Integer> root = new BinomialHeapNode<>();

    }

    public static BinomialHeapNode<Integer> getMaximum(BinomialHeapNode<Integer> node){
        return getMaximumRec(node, node.getKey());
    }

    public static BinomialHeapNode<Integer> getMaximumRec(BinomialHeapNode<Integer> node, Integer maxValue){
        return null;
    }
}
