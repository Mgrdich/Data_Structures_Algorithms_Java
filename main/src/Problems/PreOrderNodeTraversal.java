package Problems;

import adt.List;
import data_structure.ArrayList;
import data_structure.LinkedBinaryTreeNode;

public class PreOrderNodeTraversal {
    public static void main(String[] args) {

    }

    public static List<Integer> preorderTraversal(LinkedBinaryTreeNode<Integer> root) {
        ArrayList<Integer> list = new ArrayList<>();
        preorderTraversalRec(root, list);
        return list;
    }


    public static void preorderTraversalRec(LinkedBinaryTreeNode<Integer> root, ArrayList<Integer> list) {
        if (root == null) return;

        list.add(root.getElement());
        if (root.getLeft() != null) {
            preorderTraversalRec(root.getLeft(), list);
        }
        if (root.getRight() != null) {
            preorderTraversalRec(root.getRight(), list);
        }
    }
}
