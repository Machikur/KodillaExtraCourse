package com.springevents.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductImageManager implements ApplicationListener<ProductRegisterEvent> {
    @Override
    public void onApplicationEvent(ProductRegisterEvent event) {
        System.out.println("Processing image of " + event.getProductName());
        System.out.println("The graphics is: " + event.getOtherData());
    }
}
