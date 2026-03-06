package com.example.login.mapper;

import org.springframework.stereotype.Component;
import com.example.login.dto.ProductRequestDto;
import com.example.login.dto.ProductResponseDto;
import com.example.login.entity.Manufacture;
import com.example.login.entity.Product;

@Component
public class ProductMapper {

    public ProductResponseDto toDto(Product entity) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());

        if (entity.getId() != null) {
            dto.setImagePath("/api/products/image/" + entity.getId());
        }
        
        if (entity.getManufacture() != null) {
            dto.setManufactureId(entity.getManufacture().getId());
            dto.setManufactureName(entity.getManufacture().getName());
        }

        return dto;
    }

    public Product toEntity(ProductRequestDto dto, Manufacture manufacture) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setManufacture(manufacture);
        return entity;
    }

    public void updateEntity(ProductRequestDto dto, Product entity, Manufacture manufacture) {
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        entity.setManufacture(manufacture);
    }
}