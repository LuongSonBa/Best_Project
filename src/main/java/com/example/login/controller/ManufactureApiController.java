package com.example.login.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.dto.ManufactureRequestDto;
import com.example.login.dto.ManufactureResponseDto;
import com.example.login.service.ManufactureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name ="Manufacture Controller")
public class ManufactureApiController {

	private final ManufactureService manufactureService;
	private static final Logger log = LoggerFactory.getLogger(ManufactureApiController.class);
	public ManufactureApiController(ManufactureService manufactureService) {
		this.manufactureService = manufactureService;
	}

	@PostMapping("/manufactures")
	public ResponseEntity<ManufactureResponseDto> addManufacture(@Valid @RequestBody ManufactureRequestDto request) {

		ManufactureResponseDto response = manufactureService.createNewManufacture(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/manufactures")
	public List<ManufactureResponseDto> getAll() {
		return manufactureService.findAll();
	}

	@PutMapping("/manufactures/{id}")
	@Operation(summary = "Update manufacture", description = "Update manufacture information")
	public ResponseEntity<ManufactureResponseDto> updateManufacture
			(@PathVariable Long id,
			@Valid @RequestBody ManufactureRequestDto request) {
		log.info("Update Manufacture with! ID = {}", id);
		ManufactureResponseDto response = manufactureService.updateManufacture(id, request);
		log.info("Update Product successful! ID = {}", id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/manufactures/{id}")
	public ResponseEntity<Void> deleteManuById(@PathVariable Long id) {
		manufactureService.deleteManuById(id);
		return ResponseEntity.noContent().build();
	}
}
