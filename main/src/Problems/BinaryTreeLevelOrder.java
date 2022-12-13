package Problems;

import adt.List;
import adt.Queue;
import data_structure.ArrayList;
import data_structure.LinkedBinaryTreeNode;
import data_structure.LinkedQueue;

public class BinaryTreeLevelOrder {
    public static void main(String[] args) {
        LinkedBinaryTreeNode<Integer> root = new LinkedBinaryTreeNode<>(100, null, null, null);
        LinkedBinaryTreeNode<Integer> left = new LinkedBinaryTreeNode<>(90, root, null, null);
        LinkedBinaryTreeNode<Integer> right = new LinkedBinaryTreeNode<>(110, root, null, null);
        root.setLeft(left);
        root.setRight(right);

        LinkedBinaryTreeNode<Integer> right_left = new LinkedBinaryTreeNode<>(105, right, null, null);
        LinkedBinaryTreeNode<Integer> right_right = new LinkedBinaryTreeNode<>(120, right, null, null);
        right.setLeft(right_left);
        right.setRight(right_right);

        LinkedBinaryTreeNode<Integer> right_left1 = new LinkedBinaryTreeNode<>(1022, left, null, null);
        LinkedBinaryTreeNode<Integer> right_right2 = new LinkedBinaryTreeNode<>(12220, left, null, null);
        left.setLeft(right_left1);
        left.setRight(right_right2);


        System.out.println(levelOrder(root));
    }

    public static List<List<Integer>> levelOrder(LinkedBinaryTreeNode<Integer> root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;

        List<Integer> rootArr = new ArrayList<>();
        rootArr.add(root.getElement());

        list.add(rootArr);

        levelOrderRec(root.getLeft(), list, 1);
        levelOrderRec(root.getRight(), list, 1);

        return list;
    }

    public static void levelOrderRec(LinkedBinaryTreeNode<Integer> node, List<List<Integer>> total, int index) {
        if (node == null) return;

        List<Integer> currentArray = index < total.size() ? total.get(index) : null;

        if (currentArray == null) {
            currentArray = new ArrayList<>();
            total.add(currentArray);
        }

        currentArray.add(node.getElement());

        levelOrderRec(node.getLeft(), total, index + 1);
        levelOrderRec(node.getRight(), total, index + 1);
    }

}
