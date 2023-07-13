package com.calculator_for_roboskeletron.calculator;

public class Action implements CalculatorType {
    private Actions action;
    private int actionPriority;

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public Action(Actions action) {
        this.action = action;
        switch (action){
            case Addition, Subtraction -> actionPriority = 1;
            case Multiplication, Division -> actionPriority = 0;
        }
    }

    @Override
    public Answer evaluate(Answer a, Answer b) {
        Double value = null;
        switch (action){
            case Addition -> value = a.getValue() + b.getValue();
            case Subtraction -> value = a.getValue() - b.getValue();
            case Multiplication -> value = a.getValue() * b.getValue();
            case Division -> value = a.getValue() / b.getValue();
        }

        return new Answer(value);
    }

    public int getActionPriority() {
        return actionPriority;
    }
}
