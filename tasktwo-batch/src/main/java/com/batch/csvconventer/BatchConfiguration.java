package com.batch.csvconventer;

import com.batch.domain.PersonWithAge;
import com.batch.domain.PersonWithDate;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PersonWithAgeMapper personWithAgeMapper;

    @Bean
    FlatFileItemWriter<PersonWithAge> writer() {
        BeanWrapperFieldExtractor<PersonWithAge> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[]{"name", "surName", "age"});

        DelimitedLineAggregator<PersonWithAge> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(extractor);

        FlatFileItemWriter<PersonWithAge> writer = new FlatFileItemWriter<>();
        writer.setShouldDeleteIfExists(true);
        writer.setLineAggregator(aggregator);
        writer.setResource(new FileSystemResource("ConvertedPersons.csv"));

        return writer;
    }

    @Bean
    FlatFileItemReader<PersonWithDate> reader() {
        FlatFileItemReader<PersonWithDate> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("people.csv"));

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("name", "surName", "dateOfBirth");
        tokenizer.setDelimiter(",");

        DefaultLineMapper<PersonWithDate> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(personWithAgeMapper);

        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    Step changeDateToAgeStep(
            ItemReader<PersonWithDate> reader,
            ItemProcessor<PersonWithDate, PersonWithAge> processor,
            ItemWriter<PersonWithAge> writer) {

        return stepBuilderFactory.get("changeDateToAge")
                .<PersonWithDate, PersonWithAge>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    Job changeDateToAgeJob(Step step) {
        return jobBuilderFactory.get("changeDateToAge")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

}
