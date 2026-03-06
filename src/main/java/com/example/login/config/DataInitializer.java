package com.example.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.login.entity.User;
import com.example.login.repository.UserRepository;

@Component
@DependsOn("flyway")

public class DataInitializer  {
	
	@Autowired
	private UserRepository repository;

	@EventListener(ApplicationReadyEvent.class)
	public void init(ApplicationReadyEvent event) {
		if (repository.count() == 0) {
			
			User user1 = new User();
			
			user1.setUsername("luongsonba");
			user1.setPassword(("{noop}coi123"));
			
			User user2 = new User();
			
			user2.setUsername("dang");
			user2.setPassword(("{noop}dang123"));
			
			
			
			
			User user3 = new User();
			user3.setUsername("Pravinda");
			user3.setPassword(("{noop}pravinda123"));
			
			repository.save(user1);
			repository.save(user2);
			repository.save(user3);
		}
		else {
			System.out.println("Database already had 2 users");
		}

	}

	}
	

