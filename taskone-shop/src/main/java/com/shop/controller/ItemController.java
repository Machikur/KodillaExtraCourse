package com.shop.controller;

import com.shop.domain.item.Item;
import com.shop.domain.item.ItemDto;
import com.shop.domain.user.User;
import com.shop.mapper.ItemMapper;
import com.shop.repository.user.UserRepository;
import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/item")
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemMapper itemMapper;
    private final ItemService itemService;
    private final UserRepository userRepository;

    @GetMapping()
    public List<ItemDto> getListOfItems() {
        return itemMapper.mapToListDto(itemService.getAllItems());
    }

    @PostMapping
    public ResponseEntity<ItemDto> saveItem(@RequestBody ItemDto itemDto) {
        Item result = itemService.addItem(itemMapper.mapToItem(itemDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(itemMapper.mapToItemDto(result));
    }

    @PutMapping("/{index}")
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto, @PathVariable int index) {
        Item result = itemService.updateItem(itemMapper.mapToItem(itemDto), index);
        return ResponseEntity.ok(itemMapper.mapToItemDto(result));
    }

    @DeleteMapping
    public HttpStatus deleteItem(@RequestParam int index) {
        itemService.deleteItem(index);
        return HttpStatus.OK;
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

}
