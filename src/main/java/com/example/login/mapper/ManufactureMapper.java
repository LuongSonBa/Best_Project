package com.example.login.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.login.dto.ManufactureRequestDto;
import com.example.login.dto.ManufactureResponseDto;
import com.example.login.entity.Manufacture;

@Component
public class ManufactureMapper {

    private final ProductMapper productMapper;

    public ManufactureMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    // CREATE
    public Manufacture toEntity(ManufactureRequestDto dto) {
        Manufacture entity = new Manufacture();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    // UPDATE
    public void updateEntity(ManufactureRequestDto dto, Manufacture entity) {
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getAddress() != null) entity.setAddress(dto.getAddress());
        if (dto.getPhone() != null) entity.setPhone(dto.getPhone());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
    }

    // RESPONSE
    public ManufactureResponseDto toDto(Manufacture entity) {
        ManufactureResponseDto dto = new ManufactureResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setDescription(entity.getDescription());

        if (entity.getProducts() != null) {
        	dto.setProducts(
        		    entity.getProducts()
        		          .stream()
        		          .map(p -> productMapper.toDto(p))
        		          .collect(Collectors.toList())
        		);
        }

        return dto;
    }
}
// 