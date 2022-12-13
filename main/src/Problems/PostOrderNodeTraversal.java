package Problems;

import adt.List;
import data_structure.ArrayList;
import data_structure.LinkedBinaryTreeNode;

public class PostOrderNodeTraversal {
    public static void main(String[] args) {

    }

    public static List<Integer> postorderTraversal(LinkedBinaryTreeNode<Integer> root) {
        ArrayList<Integer> list = new ArrayList<>();
        postorderTraversalRec(root, list);
        return list;
    }


    public static void postorderTraversalRec(LinkedBinaryTreeNode<Integer> root, ArrayList<Integer> list) {
        if (root == null) return;

        if (root.getLeft() != null) {
            postorderTraversalRec(root.getLeft(), list);
        }
        if (root.getRight() != null) {
            postorderTraversalRec(root.getRight(), list);
        }
        list.add(root.getElement());
    }
}
