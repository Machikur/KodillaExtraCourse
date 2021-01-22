package com.mediatype.config;

import com.mediatype.domain.Book;

public class HttpMessageColonConverter extends AbstractPlainTextHttpConverter<Book> {

    public HttpMessageColonConverter() {
        super(Book.class);
    }

    @Override
    protected Book buildObjectFromMessage(String[] fields) {
        return new Book(fields[0], fields[1], Integer.parseInt(fields[2]));
    }

    @Override
    protected String setSeparator() {
        return ":";
    }

}
