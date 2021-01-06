package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookDto;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    public static Book toBook(BookDto bookDto) {
        return Book.of(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getYear());
    }

    public static BookDto toBookDto(Book book) {
        return new BookDto(book.getTitle(), book.getAuthor(), book.getYear());
    }

    public static List<Book> toListOfBook(List<BookDto> dtos) {
        return dtos.stream()
                .map(BookMapper::toBook)
                .collect(Collectors.toList());
    }

    public static List<BookDto> toListOfBookDto(List<Book> books) {
        return books.stream()
                .map(BookMapper::toBookDto)
                .collect(Collectors.toList());
    }

}
