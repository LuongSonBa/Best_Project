package com.example.login.mapper;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.example.login.dto.ComputerRequestDto;
import com.example.login.dto.ComputerResponseDto;
import com.example.login.entity.Computer;
import com.example.login.entity.Manufacture;

@Component
public class ComputerMapper {

    public ComputerResponseDto toDto(Computer entity) {
        if (entity == null) {
            return null;
        }

        ComputerResponseDto dto = new ComputerResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());

        if (entity.getPrice() != null) {
            NumberFormat jpFormat = NumberFormat.getCurrencyInstance(Locale.JAPAN);
            dto.setFormattedPrice(jpFormat.format(entity.getPrice()));
        }

        if (entity.getId() != null) {
            dto.setImagePath("/api/computers/image/" + entity.getId());
        }

        if (entity.getManufacture() != null) {
            dto.setManufactureId(entity.getManufacture().getId());
            dto.setManufactureName(entity.getManufacture().getName());
        }

        return dto;
    }

    public Computer toEntity(ComputerRequestDto dto, Manufacture manufacture) {
        if (dto == null) {
            return null;
        }

        Computer entity = new Computer();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setManufacture(manufacture);
        
        return entity;
    }

    public void updateEntity(ComputerRequestDto dto, Computer entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getPrice() != null) {
            entity.setPrice(dto.getPrice());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
    }
}
