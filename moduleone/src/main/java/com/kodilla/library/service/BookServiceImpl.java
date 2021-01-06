package com.kodilla.library.service;

import com.kodilla.library.domain.Book;
import com.kodilla.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(int index) {
        bookRepository.deleteByIndex(index);
    }

}
