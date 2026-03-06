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

import com.example.login.dto.ManufactureResponseDto;
import com.example.login.service.ManufactureService;

@Controller
public class ManufacturePageController {
	private ManufactureService manufactureService;
	private static final Logger log = LoggerFactory.getLogger(ManufacturePageController.class);

	public ManufacturePageController(ManufactureService manufactureService) {
		super();
		this.manufactureService = manufactureService;
	}

	@GetMapping("/manufactures")
	public String getManufacture(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Page<ManufactureResponseDto> manufacture = manufactureService.getManufactureListPage(PageRequest.of(page, size));
		model.addAttribute("manufactures", manufacture.getContent()); 
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", manufacture.getTotalPages()); 
		return "manufacture";
	}

	@GetMapping("/manufactures/{id}")
	public String getManuInfoById(@PathVariable Long id, Model model) {
		ManufactureResponseDto dto = manufactureService.getManufactureDetailById(id);
		model.addAttribute("manufacture", dto);
		return "manufacture-detail";
	}

	
	}