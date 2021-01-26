package com.mediatype.config;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

abstract class AbstractPlainTextHttpConverter<T> implements HttpMessageConverter<T> {

    private final Class<? extends T> t;

    protected AbstractPlainTextHttpConverter(Class<? extends T> clazz) {
        this.t = clazz;
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return clazz.getName().equals(t.getName()) &&
                mediaType.getSubtype().equals("plain") && mediaType.getType().equals("text");
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.getName().equals(t.getName()) &&
                mediaType.getSubtype().equals("plain") && mediaType.getType().equals("text");
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.TEXT_PLAIN);
    }

    @Override
    public T read(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return buildObjectFromMessage(separateMessage(inputMessage, getSeparator()));
    }

    @Override
    public void write(T t, MediaType contentType, HttpOutputMessage outputMessage) throws HttpMessageNotWritableException {
        System.out.println("im writing xd");
    }

    private String[] separateMessage(HttpInputMessage inputMessage, String regex) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(
                inputMessage.getBody(),
                Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
        }
        return builder.toString().split(regex);
    }

    protected abstract T buildObjectFromMessage(String[] resultFields);

    protected abstract String getSeparator();

}
