package com.example.login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.login.repository")
@EntityScan(basePackages = "com.example.login.entity")
@ComponentScan(basePackages = "com.example.login")//
public class LoginProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginProjectApplication.class, args);
    }
}

