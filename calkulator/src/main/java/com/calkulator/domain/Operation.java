package com.calkulator.domain;

public class Operation {

    public static Double count(Double one, Double two, OperationSymbol op) {
        switch (op) {
            case PLUS:
                return one + two;
            case MINUS:
                return one - two;
            case DIVIDE:
                return two == 0 ? 0 : one / two;
            case MULTIPLY:
                return one * two;
            default:
                throw new ArithmeticException();
        }
    }

}
