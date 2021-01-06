package com.shop.repository.item;

import com.shop.domain.item.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class ItMemoryRepository implements ItemRepository {
    List<Item> itemList = new ArrayList<>();

    @Override
    public List<Item> getAllItems() {
        return itemList;
    }

    @Override
    public Item addItem(Item item) {
        itemList.add(item);
        return item;
    }

    @Override
    public Item updateItem(Item item, int index) {
        itemList.remove(index);
        itemList.add(item);
        return item;
    }

    @Override
    public void deleteItem(int index) {
        itemList.remove(index);
    }

}
