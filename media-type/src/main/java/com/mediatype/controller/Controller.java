package com.mediatype.controller;

import com.mediatype.domain.Book;
import com.mediatype.domain.MyCustomClass;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom/")
public class Controller {

    @PostMapping(path = "add")
    public void acceptCustomTextType(@RequestBody MyCustomClass customObject) {
        System.out.println(customObject.getFieldOne());
        System.out.println(customObject.getFieldTwo());
        System.out.println(customObject.getFieldThree());
    }

    @PostMapping(path = "addBook")
    public void addBook(@RequestBody Book book) {
        System.out.println(book.getAuthor());
        System.out.println(book.getName());
        System.out.println(book.getYear());
    }

}