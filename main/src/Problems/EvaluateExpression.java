package Problems;

import adt.BinaryTree;
import adt.Position;
import data_structure.LinkedBinaryTree;


public class EvaluateExpression {
    public static void main(String[] args) {
        LinkedBinaryTree<Double> tree = new LinkedBinaryTree<>();

        Position<Double> root = tree.addRoot(-1.0);

        Position<Double> c1 = tree.addLeft(root, -1.0);
        Position<Double> c2 = tree.addRight(root, -1.0);


        Position<Double> c1_1 = tree.addLeft(c1, 5.0);
        Position<Double> c1_2 = tree.addRight(c1, 5.0);

        Position<Double> c2_1 = tree.addLeft(c2, 10.0);
        Position<Double> c2_2 = tree.addRight(c2, -1.0);


        Position<Double> c2_2_1 = tree.addLeft(c2_2, 10.0);
        Position<Double> c2_2_2 = tree.addRight(c2_2, 11.0);

        for (Position<Double> c: tree.preOrder()) {
            System.out.println(c.getElement());
        }
    }

    public Double evaluate(LinkedBinaryTree<Double> bTree) {
        return 0.0;
    }

    public Double evaluateWithValidation(LinkedBinaryTree<Double> bTree) throws IllegalArgumentException {
        validate(bTree);
        return evaluate(bTree);
    }

    private <T> void validate(LinkedBinaryTree<T> bTree) throws IllegalArgumentException {

    }

    private boolean validOperationSymbol(Double symbol) {
        return symbol == -1;
    }
}
