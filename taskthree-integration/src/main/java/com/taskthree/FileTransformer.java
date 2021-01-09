package com.taskthree;

import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FileTransformer implements GenericTransformer<File, String> {

    @Override
    public String transform(File source) {
        return "File name: \""+source.getName() + "\", created time: " + getCurrentDate();
    }
    private String getCurrentDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
