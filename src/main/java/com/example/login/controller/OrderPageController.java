package com.example.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders") 
public class OrderPageController {


    @GetMapping("/detail/{id}")
    public String showOrderDetail(@PathVariable Long id, Model model) {

        model.addAttribute("orderId", id);
        

        return "order-detail"; 
    }


    @GetMapping("/history")
    public String showOrderHistory() {
        return "order_history"; 
    }
}