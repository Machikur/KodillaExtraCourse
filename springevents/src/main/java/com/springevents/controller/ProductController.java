package com.springevents.controller;

import com.springevents.domain.ProductDto;
import com.springevents.event.ProductRegisterEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @PostMapping("createProduct")
    public void createProduct(@RequestBody ProductDto productDto) {
        publisher.publishEvent(
                new ProductRegisterEvent(this, productDto.getProductName(), productDto.getOtherData()));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}