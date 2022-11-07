package Problems;

import adt.Position;
import adt.Queue;
import adt.Tree;
import data_structure.LinkedBinaryTree;
import data_structure.LinkedQueue;

public class AverageTree {
    public static void main(String[] args) {
        LinkedBinaryTree<Double> tree = new LinkedBinaryTree<>();

        Position<Double> root = tree.addRoot(1.0);

        Position<Double> c1 = tree.addLeft(root, 2.0);
        Position<Double> c2 = tree.addRight(root, 3.0);

        Position<Double> c1_1 = tree.addLeft(c1, 3.0);
        Position<Double> c1_2 = tree.addRight(c1, 4.0);

        Position<Double> c2_1 = tree.addLeft(c2, 5.0);
        Position<Double> c2_2 = tree.addRight(c2, 6.0);

        Position<Double> c2_2_1 = tree.addLeft(c2_2, 7.0);
        Position<Double> c2_2_2 = tree.addRight(c2_2, 8.0);

        Position<Double> c2_2_1_1 = tree.addLeft(c2_2_1, 7.0);
        Position<Double> c2_2_2_2 = tree.addRight(c2_2_1, 8.0);

        System.out.println(averagePreOrder(tree));
        System.out.println(averageBreadthFirstTraversal(tree));
    }

    public static Double averagePreOrder(Tree<Double> tree) {
        if (tree.root() == null)
            return 0.0;
        return averagePreOrder(tree, tree.root());
    }

    public static Double averageBreadthFirstTraversal(Tree<Double> tree) {
        if (tree.root() == null)
            return 0.0;

        double sum = 0;

        Queue<Position<Double>> fringe = new LinkedQueue<>();
        fringe.enqueue(tree.root());
        while (!fringe.isEmpty()) {
            Position<Double> p = fringe.dequeue();
            sum += p.getElement();
            for (Position<Double> c : tree.children(p)) {
                fringe.enqueue(c);
            }
        }

        return sum / tree.size();
    }

    public static Double averagePreOrder(Tree<Double> tree, Position<Double> node) {
        Double sum = node.getElement(); // visit
        for (Position<Double> childPosition : tree.children(node)) {
            sum += averagePreOrder(tree, childPosition);
        }

        if (tree.isRoot(node)) {
            return sum / tree.size();
        }

        return sum;
    }

    public static Double averageBreadthFirstTraversal(Tree<Double> tree, Position<Double> Node) {
        return 0.0;
    }
}
