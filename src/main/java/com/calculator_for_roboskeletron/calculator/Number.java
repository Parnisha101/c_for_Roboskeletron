package com.calculator_for_roboskeletron.calculator;

public class Number implements CalculatorType {
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Number(Double value) {
        this.value = value;
    }

    @Override
    public Answer evaluate(Answer a, Answer b) {
        return new Answer(value);
    }
}
