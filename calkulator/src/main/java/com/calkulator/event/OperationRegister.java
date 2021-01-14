package com.calkulator.event;

import com.calkulator.domain.OperationSymbol;
import org.springframework.context.ApplicationEvent;

public class OperationRegister extends ApplicationEvent {

    private Double one;
    private Double two;
    private OperationSymbol operationSymbol;


    public OperationRegister(Object source, Double one, Double two, OperationSymbol operationSymbol) {
        super(source);
        this.one = one;
        this.two = two;
        this.operationSymbol = operationSymbol;
    }

    public Double getOne() {
        return one;
    }

    public Double getTwo() {
        return two;
    }

    public OperationSymbol getOperationSymbol() {
        return operationSymbol;
    }

}
