package com.shop.mapper;

import com.shop.domain.item.Item;
import com.shop.domain.item.ItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public Item mapToItem(ItemDto itemDto) {
        return new Item(itemDto.getName(), itemDto.getCost());
    }

    public ItemDto mapToItemDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getCost());
    }

    public List<ItemDto> mapToListDto(List<Item> items) {
        return items.stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toList());
    }

}
