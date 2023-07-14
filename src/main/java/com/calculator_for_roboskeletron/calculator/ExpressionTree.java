package com.calculator_for_roboskeletron.calculator;

import java.util.Stack;

public class ExpressionTree implements CalculatorType {
    private class Node {
        public CalculatorType value;
        public Node left;
        public Node right;

        public Node(CalculatorType value) {
            this.value = value;
        }

        public Answer evaluate() {
            if (!(value instanceof Action))
                return value.evaluate(null, null);

            return value.evaluate(left.evaluate(), right.evaluate());
        }
    }

    private Node root;
    private final ExpressionTree parent;
    private ExpressionTree currentFunction;
    private final Stack<ExpressionTree> functionStack = new Stack<>();

    public ExpressionTree(ExpressionTree parent){
        this.parent = parent;
    }

    public ExpressionTree(){
        this(null);
    }

    public void put(CalculatorType value) {
        if (currentFunction == null) {
            Node node = new Node(value);
            root = put(root, node);
            return;
        }

        currentFunction.put(value);
    }

    private Node put(Node root, Node node) {
        if (root == null)
            return node;

        if (root.value instanceof Number && node.value instanceof Number)
            throw new IllegalArgumentException();

        if (root.value instanceof Action rootAction) {
            if (node.value instanceof Action nodeAction)
                return actionPutAction(root, node, rootAction, nodeAction);
            if (node.value instanceof MathFunc mathFunc)
                return actionPutMathFunc(root, node, mathFunc);

            if (root.right == null)
                root.right = node;
            else if (root.right.value instanceof Action)
                root.right = put(root.right, node);
            else
                throw new IllegalArgumentException();

            return root;
        }
        
        return numberPutAction(root, node);
    }

    private static Node numberPutAction(Node root, Node node) {
        node.left = root;
        return node;
    }

    private Node actionPutAction(Node root, Node node, Action rootAction, Action nodeAction) {
        int compareResult = Integer.compare(rootAction.getActionPriority(), nodeAction.getActionPriority());

        if (compareResult <= 0) {
            node.left = root;
            return node;
        }

        root.right = put(root.right, node);
        return root;
    }

    private Node actionPutMathFunc(Node root, Node node, MathFunc mathFunc) {
        if (mathFunc.isOpen()){
            if (root.right != null){
                root.right = put(root.right, node);
                return root;
            }

            root.right = node;
            ExpressionTree functionParameter = new ExpressionTree(this);
            mathFunc.setFunctionParameter(functionParameter);
            currentFunction = functionParameter;
        }
        else
            parent.closeMathFunc();

        return root;
    }

    private void closeMathFunc(){
        functionStack.push(currentFunction);
        currentFunction = null;
    }

    public Answer evaluate() {
        return evaluate(null, null);
    }

    @Override
    public Answer evaluate(Answer a, Answer b) {
        return root.evaluate();
    }
}
