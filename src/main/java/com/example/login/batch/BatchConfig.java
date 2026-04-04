package com.example.login.batch;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.login.entity.Computer;
import com.example.login.entity.Status;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
// abc
    @Bean
    public JpaPagingItemReader<Computer> reader(EntityManagerFactory emf) {

        JpaPagingItemReader<Computer> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emf);

        reader.setQueryString(
            "SELECT c FROM Computer c WHERE c.status = 'ACTIVE'"
        );

        reader.setPageSize(100);

        return reader;
    }

    @Bean
    public ItemProcessor<Computer, Computer> processor() {
        return computer -> {

            if (computer.getExpiredDate() != null &&
                computer.getExpiredDate().isBefore(LocalDateTime.now())) {

                computer.setStatus(Status.EXPIRED);
                return computer;
            }

            return null;
        };
    }

    @Bean(name = "batchWriter")
    public JpaItemWriter<Computer> writer(EntityManagerFactory emf) {

        JpaItemWriter<Computer> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);

        return writer;
    }

    @Bean
    public Step expireStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           JpaPagingItemReader<Computer> reader,
                           ItemProcessor<Computer, Computer> processor,
                           JpaItemWriter<Computer> writer) {

        return new StepBuilder("expireStep", jobRepository)
                .<Computer, Computer>chunk(100, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job expireJob(JobRepository jobRepository, Step expireStep) {

        return new JobBuilder("expireJob", jobRepository)
                .start(expireStep)
                .build();
    }
}