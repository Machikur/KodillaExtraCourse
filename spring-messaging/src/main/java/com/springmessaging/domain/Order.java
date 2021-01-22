package com.springmessaging.domain;

import java.io.Serializable;

public class Order implements Serializable {

    private String orderName;
    private double price;
    private int quantity;

    public Order(String orderName, double price, int quantity) {
        this.orderName = orderName;
        this.price = price;
        this.quantity = quantity;
    }

    public Order() {
    }

    public String getOrderName() {
        return orderName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

}
