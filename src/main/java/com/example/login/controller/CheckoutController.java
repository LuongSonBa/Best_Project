package com.example.login.controller;

import com.example.login.service.UserService;
import com.example.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

    @Autowired
    private UserService userService;

    @GetMapping("/checkout")
    public String showCheckoutPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
    	
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);

        model.addAttribute("user", user);
        
        return "checkout"; 
    }
}