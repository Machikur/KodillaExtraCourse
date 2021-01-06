package com.shop.domain.item;

import lombok.Value;

@Value(staticConstructor = "of")
public class Item {
    String name;
    double cost;

}
