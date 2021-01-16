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
        return String.format("File name: %s , created time: %s ", source.getName(), getCurrentDate());
    }

    private String getCurrentDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
