package com.batch.csvconventer;

import com.batch.domain.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) {
        return new Product(item.getId(), item.getQuantity(), (int) (item.getPrice() * 1.1));
    }
}
