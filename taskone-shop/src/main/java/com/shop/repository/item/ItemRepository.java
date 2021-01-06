package com.shop.repository.item;

import com.shop.domain.item.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> getAllItems();
    Item addItem(Item item);
    Item updateItem(Item item,int index);
    void deleteItem(int index);
}
