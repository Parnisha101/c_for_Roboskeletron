package com.calculator_for_roboskeletron.calculator;

public class ExpressionTree {
    private class Node {
        public CalculatorType value;
        public Node left;
        public Node right;

        public Node(CalculatorType value) {
            this.value = value;
        }

        public Answer evaluate(){
            if (value instanceof Number)
                return value.evaluate(null, null);

            return value.evaluate(left.evaluate(),  right.evaluate());
        }
    }

    private Node root;

    public void put(CalculatorType value) {
        Node node = new Node(value);
        root = put(root, node);
    }

    private Node put(Node root, Node node) {
        if (root == null)
            return node;

        if (root.value instanceof Number && node.value instanceof Number)
            throw new IllegalArgumentException();

        if (root.value instanceof Action rootAction && node.value instanceof Action nodeAction) {
            int compareResult = Integer.compare(rootAction.getActionPriority(), nodeAction.getActionPriority());

            if (compareResult < 0) {
                node.left = root;
                return node;
            }

            root.right = put(root.right, node);
            return root;
        }

        if (root.value instanceof Number && node.value instanceof Action){
            node.left = root;
            return node;
        }

        if (root.right == null)
            root.right = node;
        else if (root.left == null)
            root.left = node;
        else if (root.right.value instanceof Action)
            root.right = put(root.right, node);
        else
            throw new IllegalArgumentException();

        return root;
    }

    public double evaluate(){
        return root.evaluate().getValue();
    }
}
