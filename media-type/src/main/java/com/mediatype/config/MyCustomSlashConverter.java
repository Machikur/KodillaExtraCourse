package com.mediatype.config;

import com.mediatype.domain.MyCustomClass;

public class MyCustomSlashConverter extends AbstractPlainTextHttpConverter<MyCustomClass> {

    public MyCustomSlashConverter() {
        super(MyCustomClass.class);
    }

    @Override
    protected MyCustomClass buildObjectFromMessage(String[] fields) {
        return new MyCustomClass(fields[0], fields[1], fields[2]);
    }

    @Override
    protected String getSeparator() {
        return "/";
    }

}
