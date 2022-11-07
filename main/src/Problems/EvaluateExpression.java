package Problems;

import adt.Position;
import data_structure.LinkedBinaryTree;


public class EvaluateExpression {
    public static void main(String[] args) {
        LinkedBinaryTree<Double> tree = new LinkedBinaryTree<>();

        Position<Double> root = tree.addRoot(-1.0);

        Position<Double> c1 = tree.addLeft(root, -1.0);
        Position<Double> c2 = tree.addRight(root, -1.0);


        Position<Double> c1_1 = tree.addLeft(c1, -1.0);
        Position<Double> c1_2 = tree.addRight(c1, 5.0);

        Position<Double> c1_1_1 = tree.addLeft(c1_1, 5.0);
        Position<Double> c1_1_2 = tree.addRight(c1_1, 5.0);

        Position<Double> c2_1 = tree.addLeft(c2, 10.0);
        Position<Double> c2_2 = tree.addRight(c2, -1.0);


        Position<Double> c2_2_1 = tree.addLeft(c2_2, 10.0);
        Position<Double> c2_2_2 = tree.addRight(c2_2, 11.0);

        System.out.println(evaluateWithValidation(tree));
    }

    public static Double evaluate(LinkedBinaryTree<Double> bTree) {
        if (bTree.root() == null)
            return 0.0;

        return evaluate(bTree, bTree.root());
    }

    private static Double evaluate(LinkedBinaryTree<Double> bTree, Position<Double> node) {
        Position<Double> leftPart = bTree.left(node);
        Position<Double> rightPart = bTree.right(node);

        boolean isLeftExternal = bTree.isExternal(leftPart);
        boolean isRightExternal = bTree.isExternal(rightPart);

        // base case is embedded in this expression
        Double leftValue = isLeftExternal ? leftPart.getElement() : evaluate(bTree, leftPart);
        Double rightValue = isRightExternal ? rightPart.getElement() : evaluate(bTree, rightPart);

        return evaluateTheOperation('+', leftValue, rightValue);
    }

    public static Double evaluateWithValidation(LinkedBinaryTree<Double> bTree) throws IllegalArgumentException {
        if (bTree.root() == null)
            return 0.0;
        validate(bTree);
        return evaluate(bTree);
    }

    private static <T> void validate(LinkedBinaryTree<T> bTree) throws IllegalArgumentException {
        if (bTree.root() == null) {
            return;
        }
        validate(bTree, bTree.root());
    }

    private static <T> void validate(LinkedBinaryTree<T> bTree, Position<T> node) throws IllegalArgumentException {
        Position<T> leftPart = bTree.left(node);
        Position<T> rightPart = bTree.right(node);


        if (bTree.isInternal(node) && !validOperationSymbol((Double) node.getElement())) {
            throw new IllegalArgumentException("Not a Valid Expression tree");
        }


        if (leftPart != null) {
            validate(bTree, leftPart);
        }

        if (rightPart != null) {
            validate(bTree, rightPart);
        }
    }

    private static boolean validOperationSymbol(Double symbol) {
        return symbol == -1;
    }

    private static Double evaluateTheOperation(Character operation, Double leftExpression, Double rightExpression) {
        switch (operation) {
            case '*':
                return leftExpression * rightExpression;
            case '+':
                return leftExpression + rightExpression;
            case '/':
                return leftExpression / rightExpression;
            case '-':
                return leftExpression - rightExpression;
            default:
                throw new IllegalArgumentException("Something went wrong");
        }
    }
}
