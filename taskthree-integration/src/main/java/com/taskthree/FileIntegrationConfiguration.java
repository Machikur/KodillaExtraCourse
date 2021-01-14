package com.taskthree;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
public class FileIntegrationConfiguration {

    @Bean
    IntegrationFlow integrationFlow(
            FileReadingMessageSource source,
            FileWritingMessageHandler handler,
            FileTransformer transformer) {

        return IntegrationFlows.from(source, event -> event.poller(Pollers.fixedDelay(100)))
                .transform(transformer)
                .handle(handler)
                .get();
    }

    @Bean
    FileReadingMessageSource fileAdapter() {
        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File("data/files"));
        return fileSource;
    }

    @Bean
    FileWritingMessageHandler informationAdapter() {
        File directory = new File("data/history");
        FileWritingMessageHandler writer = new FileWritingMessageHandler(directory);

        writer.setFileNameGenerator(message -> "history.txt");
        writer.setFileExistsMode(FileExistsMode.APPEND);
        writer.setExpectReply(false);
        writer.setAppendNewLine(true);
        return writer;
    }


}
