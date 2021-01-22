package com.springmessaging.controller;

import com.springmessaging.domain.Order;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final JmsTemplate template;

    public OrderController(JmsTemplate template) {
        this.template = template;
    }

    @PostMapping
    public void createOrder(@RequestBody Order order) {
        template.send("orderMessage", m -> m.createObjectMessage(order));
    }

}
