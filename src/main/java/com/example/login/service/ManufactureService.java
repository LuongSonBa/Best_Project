package com.example.login.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.login.dto.ManufactureRequestDto;
import com.example.login.dto.ManufactureResponseDto;
import com.example.login.entity.Manufacture;
import com.example.login.exception.NotFoundException;
import com.example.login.mapper.ManufactureMapper;
import com.example.login.repository.ManufactureRepository;

import jakarta.transaction.Transactional;

@Service
public class ManufactureService {

    private final ManufactureRepository manufactureRepository;
    private final ManufactureMapper manufactureMapper;
    private static final Logger log =
            LoggerFactory.getLogger(ManufactureService.class);

    public ManufactureService(ManufactureRepository manufactureRepository,
                              ManufactureMapper manufactureMapper) {
        this.manufactureRepository = manufactureRepository;
        this.manufactureMapper = manufactureMapper;
    }

    // LIST (PAGE)
    public Page<ManufactureResponseDto> getManufactureListPage(Pageable pageable) {
        Page<Manufacture> page = manufactureRepository.findAll(pageable);

        List<ManufactureResponseDto> dtoList = page.getContent()
                .stream()
                .map(manufactureMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    // CREATE
    public ManufactureResponseDto createNewManufacture(ManufactureRequestDto dto) {

        if (manufactureRepository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Manufacture's name already exists");
        }

        Manufacture entity = manufactureMapper.toEntity(dto);
        Manufacture saved = manufactureRepository.save(entity);

        return manufactureMapper.toDto(saved);
    }

    public ManufactureResponseDto getManufactureDetailById(Long id) {
        Manufacture manu = manufactureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manufacture not found"));
        return manufactureMapper.toDto(manu);
    }


    @Transactional
    public ManufactureResponseDto updateManufacture(Long id, ManufactureRequestDto dto) {

        log.info("Start updating Manufacture, id={}", id);

        Manufacture manu = manufactureRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Manufacture not found, id={}", id);
                    return new NotFoundException("Manufacture not found with id: " + id);
                });

        log.debug("Manufacture found, id={}, current data={}", id, manu);

        manufactureMapper.updateEntity(dto, manu);

        log.debug("Manufacture mapped with new data, id={}", id);

        Manufacture updated = manufactureRepository.save(manu);

        log.info("Update Manufacture success, id={}", updated.getId());

        return manufactureMapper.toDto(updated);
    }



    public void deleteManuById(Long id) {
        if (!manufactureRepository.existsById(id)) {
            throw new RuntimeException("Manufacture with id " + id + " is not found");
        }
        manufactureRepository.deleteById(id);
    }

    public List<ManufactureResponseDto> findAll() {
        return manufactureRepository.findAll()
                .stream()
                .map(manufactureMapper::toDto)
                .collect(Collectors.toList());
    }
}
