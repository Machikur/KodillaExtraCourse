package com.kodilla;

import java.util.List;

public class BuddyApp {
    public static void main(String[] args) {
        List<Book> books = BooksGenerator.generate(100);
        BooksFilter booksFilter = new BooksFilter(books);
        List<Book> filteredBooks = booksFilter.onlyBooksOlderThan(20);
        System.out.println(filteredBooks.size());
        System.out.println("cos tam");

    }

}
