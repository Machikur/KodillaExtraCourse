package com.springevents.event;

import org.springframework.context.ApplicationEvent;

public class ProductRegisterEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     * which the event is associated (never {@code null})
     */
    private String productName;
    private String otherData;

    public ProductRegisterEvent(Object source, String productName, String otherData) {
        super(source);
        this.productName = productName;
        this.otherData = otherData;
    }

    public String getProductName() {
        return productName;
    }

    public String getOtherData() {
        return otherData;
    }
}
