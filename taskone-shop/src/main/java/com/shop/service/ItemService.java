package com.shop.service;

import com.shop.domain.item.Item;
import com.shop.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }

    public Item addItem(Item item) {
        return itemRepository.addItem(item);
    }

    public Item updateItem(Item item, int index) {
        return itemRepository.updateItem(item, index);
    }

    public void deleteItem(int index) {
        itemRepository.deleteItem(index);
    }

}
