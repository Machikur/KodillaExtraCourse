package com.springmessaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringmessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmessagingApplication.class, args);
    }

}
