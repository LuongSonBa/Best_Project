package com.example.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.login.dto.ComputerResponseDto;
import com.example.login.service.ComputerService;
import com.example.login.service.ManufactureService;

@Controller
public class ComputerPageController {

    private final ComputerService computerService;
    private final ManufactureService manufactureService;
    private static final Logger log = LoggerFactory.getLogger(ComputerPageController.class);

    public ComputerPageController(ComputerService computerService, ManufactureService manufactureService) {
        this.computerService = computerService;
        this.manufactureService = manufactureService;
    }

    @GetMapping("/computers")
    public String showComputers(Model model, 
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "4") int size) { 
        log.info("Request list computers page={}, size={}", page, size);
        
        Page<ComputerResponseDto> computerPage = computerService.getComputersPage(PageRequest.of(page, size));

        model.addAttribute("computers", computerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", computerPage.getTotalPages());

        return "computers";
    }

    @GetMapping("/computers/{id}")
    public String getComputerDetail(@PathVariable Long id, Model model) {
        log.info("Request computer detail with id={}", id);
        
        ComputerResponseDto computer = computerService.getComputerById(id);
        log.info("Computer loaded successfully: id={}, name={}", computer.getId(), computer.getName());
        
        model.addAttribute("computer", computer);
        model.addAttribute("manufactures", manufactureService.findAll());

        return "computers-detail"; 
    }

    @GetMapping("/computers/add")
    public String showAddForm(Model model) {
        log.info("Request show add computer form");
        model.addAttribute("manufactures", manufactureService.findAll());
        return "computer-add";
    }
}