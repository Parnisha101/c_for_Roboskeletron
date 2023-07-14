package com.calculator_for_roboskeletron.calculator;

import java.util.function.Function;

public class MathFunc implements CalculatorType {
    private final boolean isOpen;
    private Function<Answer, Answer> function;
    private ExpressionTree functionParameter;

    public boolean isOpen() {
        return isOpen;
    }

    public ExpressionTree getFunctionParameter() {
        return functionParameter;
    }

    public void setFunctionParameter(ExpressionTree functionParameter) {
        this.functionParameter = functionParameter;
    }

    public MathFunc(Function<Answer, Answer> function) {
        this.function = function;
        isOpen = true;
    }

    public MathFunc(){
        isOpen = false;
    }

    @Override
    public Answer evaluate(Answer a, Answer b) {
        return function.apply(functionParameter.evaluate());
    }

    public static Function<Answer, Answer> emptyFunc(){
        return answer -> answer;
    }

    public static Function<Answer, Answer> wrappedFunc(Function<Double, Double> function){
        return answer -> {
            double value = function.apply(answer.getValue());
            return new Answer(value);
        };
    }
}
