package com.springmessaging.receiver;

import com.springmessaging.domain.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderReceiver {

    @JmsListener(containerFactory = "jmsFactory", destination = "orderMessage")
    public void receive(Order order) {
        System.out.println("Zamówienie złozone: " + order.toString());

    }
}
