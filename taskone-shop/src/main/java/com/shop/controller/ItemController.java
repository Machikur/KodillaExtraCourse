package com.shop.controller;

import com.shop.domain.item.Item;
import com.shop.domain.item.ItemDto;
import com.shop.mapper.ItemMapper;
import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/item")
@RestController
@RequiredArgsConstructor
class ItemController {

    private final ItemMapper itemMapper;
    private final ItemService itemService;

    @GetMapping()
    public List<ItemDto> getListOfItems() {
        return itemMapper.mapToListDto(itemService.getAllItems());
    }

    @PostMapping
    public ResponseEntity<ItemDto> saveItem(@RequestBody ItemDto itemDto) {
        Item result = itemService.addItem(itemMapper.mapToItem(itemDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(itemMapper.mapToItemDto(result));
    }

    @PutMapping
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto) {
        Item result = itemService.updateItem(itemMapper.mapToItem(itemDto));
        return ResponseEntity.ok(itemMapper.mapToItemDto(result));
    }

    @DeleteMapping
    public void deleteItem(@RequestParam Long id) {
        itemService.deleteItem(id);
    }

}
