package com.mediatype.config;

import com.mediatype.domain.Book;
import com.mediatype.domain.MyCustomClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class ConfigurationClass {

    @Bean
    HttpMessageConverter<MyCustomClass> getConverter() {
        return new MyCustomSlashConverter();
    }

    @Bean
    HttpMessageConverter<Book> bookConverter() {
        return new HttpMessageColonConverter();
    }
}
