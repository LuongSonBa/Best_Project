package com.example.login;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.login.repository")
@EntityScan(basePackages = "com.example.login.entity")
@ComponentScan(basePackages = "com.example.login")//
@EnableBatchProcessing
@EnableScheduling
public class LoginProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginProjectApplication.class, args);
    }
}

