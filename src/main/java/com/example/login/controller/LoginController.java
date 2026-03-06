package com.example.login.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	public static void main(String[] args) {
		
	}

    @GetMapping("/login")
    public String login() {
        return "login";  
    }

    @GetMapping("/home")
    public String home() {
        return "home"; 
    }
}
