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

import com.example.login.dto.ProductResponseDto;
import com.example.login.service.ManufactureService;
import com.example.login.service.ProductService;

@Controller
public class ProductPageController {

	private final ProductService productService;
	private final ManufactureService manufactureService;
	private static final Logger log = LoggerFactory.getLogger(ProductPageController.class);

	public ProductPageController(ProductService productService, ManufactureService manufactureService) {
		this.productService = productService;
		this.manufactureService = manufactureService;
	}

	@GetMapping("/products")
	public String showProducts(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size) {
		log.info("Request list products page={}, size={}", page, size);
		Page<ProductResponseDto> productPage = productService.getProductsPage(PageRequest.of(page, size));

		model.addAttribute("products", productPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", productPage.getTotalPages());

		return "products";
	}

	@GetMapping("/products/{id}")
	public String getProductDetail(@PathVariable Long id, Model model) {
		log.info("Request product detail with id={}", id);
		ProductResponseDto product = productService.getProductById(id);
		log.info("Product loaded successfully: id={}, name={}", product.getId(), product.getName());
		model.addAttribute("product", product);
		model.addAttribute("manufactures", manufactureService.findAll());

		return "product-detail";
	}


	@GetMapping("/products/add")
	public String showAddForm(Model model) {
		model.addAttribute("manufactures", manufactureService.findAll());
		return "product-add";
	}
}
