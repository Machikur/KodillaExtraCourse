package com.batch;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

@Component
public class Writer {

    @PostConstruct
    public void write() throws IOException {
        File file = new File(getClass().getClassLoader().getResources("People.sql").toString());
        Random random = new Random();
        if (file.createNewFile()) {
            FileWriter fileWriter = new FileWriter(file);
            for (int id = 1; id < 1000; id++) {
                fileWriter.write(
                        "Krzysztof" + id + "," +
                                "Kowalski" + id + "," +
                                LocalDate.of(random.nextInt(100) + 1919
                                        , (random.nextInt(11) + 1),
                                        random.nextInt(27) + 1).toString() + "\n");
            }
            fileWriter.close();
        }
    }
}
