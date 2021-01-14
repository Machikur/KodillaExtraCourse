package com.calkulator.controller;

import com.calkulator.domain.Operation;
import com.calkulator.domain.OperationSymbol;
import com.calkulator.event.OperationRegister;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.calkulator.domain.OperationSymbol.*;

@RequestMapping
@RestController
public class AppController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @PostMapping("plus")
    public Double plus(@RequestParam double one, @RequestParam double two) {
        registerOperationEvent(one, two, PLUS);
        return Operation.count(one, two, PLUS);
    }

    @PostMapping("minus")
    public Double minus(@RequestParam double one, @RequestParam double two) {
        registerOperationEvent(one, two, MINUS);
        return Operation.count(one, two, MINUS);
    }

    @PostMapping("divide")
    public Double divide(@RequestParam double one, @RequestParam double two) {
        registerOperationEvent(one, two, DIVIDE);
        return Operation.count(one, two, DIVIDE);
    }

    @PostMapping("multiply")
    public Double multiply(@RequestParam double one, @RequestParam double two) {
        registerOperationEvent(one, two, MULTIPLY);
        return Operation.count(one, two, MULTIPLY);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    private void registerOperationEvent(double one, double two, OperationSymbol op) {
        publisher.publishEvent
                (new OperationRegister(this, one, two, op));
    }

}
