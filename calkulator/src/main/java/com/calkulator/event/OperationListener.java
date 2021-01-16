package com.calkulator.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class OperationListener implements ApplicationListener<OperationEvent> {

    Logger logger = Logger.getGlobal();

    @Override
    public void onApplicationEvent(OperationEvent event) {
        logger.log(Level.INFO, "Przeprowadzona operacja to " + event.getOperationSymbol() +
                " na liczbach: " + event.getOne() + " i " + event.getTwo());
    }
}
