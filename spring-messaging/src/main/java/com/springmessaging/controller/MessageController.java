package com.springmessaging.controller;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final JmsTemplate template;

    public MessageController(JmsTemplate template) {
        this.template = template;
    }

    @GetMapping("/process")
    public void processMessage(@RequestParam String message) {
        template.convertAndSend("queue-test", message);
    }


}
