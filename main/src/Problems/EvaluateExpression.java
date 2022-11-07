package Problems;

import adt.Position;
import data_structure.LinkedBinaryTree;


public class EvaluateExpression {
    public static void main(String[] args) {
        LinkedBinaryTree<Character> tree = new LinkedBinaryTree<>();

        Position<Character> root = tree.addRoot('+');

        Position<Character> c1 = tree.addLeft(root, '+');
        Position<Character> c2 = tree.addRight(root, '+');


        Position<Character> c1_1 = tree.addLeft(c1, '+');
        Position<Character> c1_2 = tree.addRight(c1, 'A');

        Position<Character> c1_1_1 = tree.addLeft(c1_1, 'A');
        Position<Character> c1_1_2 = tree.addRight(c1_1, 'A');

        Position<Character> c2_1 = tree.addLeft(c2, 'A');
        Position<Character> c2_2 = tree.addRight(c2, '+');


        Position<Character> c2_2_1 = tree.addLeft(c2_2, 'A');
        Position<Character> c2_2_2 = tree.addRight(c2_2, 'A');

        System.out.println(evaluateWithValidation(tree));
    }

    public static Double evaluate(LinkedBinaryTree<Character> bTree) {
        if (bTree.root() == null)
            return 0.0;

        return evaluate(bTree, bTree.root());
    }

    private static Double evaluate(LinkedBinaryTree<Character> bTree, Position<Character> node) {
        Position<Character> leftPart = bTree.left(node);
        Position<Character> rightPart = bTree.right(node);

        boolean isLeftExternal = bTree.isExternal(leftPart);
        boolean isRightExternal = bTree.isExternal(rightPart);

        // base case is embedded in this expression
        double leftValue = isLeftExternal ? leftPart.getElement() : evaluate(bTree, leftPart);
        double rightValue = isRightExternal ? rightPart.getElement() : evaluate(bTree, rightPart);

        return evaluateTheOperation(node.getElement(), leftValue, rightValue);
    }

    public static Double evaluateWithValidation(LinkedBinaryTree<Character> bTree) throws IllegalArgumentException {
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


        if (bTree.isInternal(node) && !validOperationSymbol((Character) node.getElement())) {
            throw new IllegalArgumentException("Not a Valid Expression tree");
        }


        if (leftPart != null) {
            validate(bTree, leftPart);
        }

        if (rightPart != null) {
            validate(bTree, rightPart);
        }
    }

    private static boolean validOperationSymbol(Character symbol) {
        return symbol == '+' || symbol == '*' || symbol == '/' || symbol == '-';
    }

    private static Double evaluateTheOperation(Character operation, double leftExpression, double rightExpression) {
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
