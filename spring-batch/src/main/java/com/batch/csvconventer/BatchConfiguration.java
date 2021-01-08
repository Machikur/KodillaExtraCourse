package com.batch.csvconventer;

import com.batch.domain.Product;
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
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
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

    @Bean
    FlatFileItemWriter<Product> fileItemWriter() {
        BeanWrapperFieldExtractor<Product> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[]{"id", "quantity", "price"});

        DelimitedLineAggregator<Product> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(extractor);

        FlatFileItemWriter<Product> writer = new FlatFileItemWriter<>();
        writer.setLineAggregator(aggregator);
        writer.setResource(new FileSystemResource("output.csv"));
        writer.setShouldDeleteIfExists(true);

        return writer;
    }

    @Bean
    FlatFileItemReader<Product> fileItemReader() {
        FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data.csv"));

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "quantity", "price");
        tokenizer.setDelimiter(",");

        BeanWrapperFieldSetMapper<Product> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(Product.class);

        DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
        lineMapper.setFieldSetMapper(mapper);
        lineMapper.setLineTokenizer(tokenizer);

        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    ProductProcessor processor() {
        return new ProductProcessor();
    }

    @Bean
    Step priceChange(
            ItemReader<Product> reader,
            ItemProcessor<Product, Product> processor,
            ItemWriter<Product> writer) {

        return stepBuilderFactory.get("priceChange")
                .<Product, Product>chunk(50)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    Job changePriceJob(Step priceChange) {
        return jobBuilderFactory.get("priceChange")
                .incrementer(new RunIdIncrementer())
                .flow(priceChange)
                .end()
                .build();
    }

}
