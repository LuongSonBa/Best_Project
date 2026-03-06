package com.example.login.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.login.dto.ProductRequestDto;
import com.example.login.dto.ProductResponseDto;
import com.example.login.entity.Manufacture;
import com.example.login.entity.Product;
import com.example.login.exception.NotFoundException;
import com.example.login.mapper.ProductMapper;
import com.example.login.repository.ManufactureRepository;
import com.example.login.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

	private final ProductRepository productRepository;
	private final ManufactureRepository manufactureRepository;
	private final ProductMapper productMapper;
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	public ProductService(ProductRepository productRepository, ManufactureRepository manufactureRepository,
			ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.manufactureRepository = manufactureRepository;
		this.productMapper = productMapper;
	}

	public List<ProductResponseDto> getAllProducts() {
		return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
	}


	public Page<ProductResponseDto> getProductsPage(Pageable pageable) {

		log.info("Load products page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());

		return productRepository.findAll(pageable).map(productMapper::toDto);

	}

	public ProductResponseDto getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
		return productMapper.toDto(product);
	}

	public byte[] getProductImage(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
		return product.getImage(); 
	}

	public ProductResponseDto createProduct(ProductRequestDto dto, MultipartFile imageFile) throws IOException {
		Manufacture manufacture = null;
		if (dto.getManufactureId() != null) {
			manufacture = manufactureRepository.findById(dto.getManufactureId())
					.orElseThrow(() -> new NotFoundException("Manufacture not found"));
		}

		Product entity = productMapper.toEntity(dto, manufacture);

		if (imageFile != null && !imageFile.isEmpty()) {
			entity.setImage(imageFile.getBytes());
		}

		Product saved = productRepository.save(entity);
		return productMapper.toDto(saved);
	}

	public ProductResponseDto updateProduct(Long id, ProductRequestDto dto, MultipartFile imageFile)
			throws IOException {
		Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));

		Manufacture manufacture = null;
		if (dto.getManufactureId() != null) {
			manufacture = manufactureRepository.findById(dto.getManufactureId())
					.orElseThrow(() -> new NotFoundException("Manufacture not found"));
		}

		if (imageFile != null && !imageFile.isEmpty()) {
			product.setImage(imageFile.getBytes());
		}

		productMapper.updateEntity(dto, product, manufacture);
		Product updated = productRepository.save(product);
		return productMapper.toDto(updated);
	}

	public void deleteProductById(Long id) {
		if (!productRepository.existsById(id)) {
			throw new NotFoundException("Product not found");
		}
		productRepository.deleteById(id);
	}
}