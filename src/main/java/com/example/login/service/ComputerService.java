package com.example.login.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.login.dto.ComputerRequestDto;
import com.example.login.dto.ComputerResponseDto;
import com.example.login.entity.Computer;
import com.example.login.entity.Manufacture;
import com.example.login.exception.NotFoundException;
import com.example.login.mapper.ComputerMapper;
import com.example.login.repository.ComputerRepository;
import com.example.login.repository.ManufactureRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class ComputerService {
    private final ComputerRepository computerRepository;
    private final ManufactureRepository manufactureRepository;
    private final ComputerMapper computerMapper;
    private static final Logger log = LoggerFactory.getLogger(ComputerService.class);

    public ComputerService(ComputerRepository computerRepository, 
                           ManufactureRepository manufactureRepository,
                           ComputerMapper computerMapper) {
        this.computerRepository = computerRepository;
        this.manufactureRepository = manufactureRepository;
        this.computerMapper = computerMapper;
    }

    public Page<ComputerResponseDto> getComputersPage(Pageable pageable) {
        log.info("Load computers page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return computerRepository.findAll(pageable).map(computerMapper::toDto);
    }

    public ComputerResponseDto getComputerById(Long id) {
        Computer computer = computerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Computer not found"));
        return computerMapper.toDto(computer);
    }

    public byte[] getComputerImage(Long id) {
        Computer computer = computerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Computer not found"));
        return computer.getImage();
    }

    public ComputerResponseDto createComputer(ComputerRequestDto dto, MultipartFile imageFile) throws IOException {
        Manufacture manufacture = manufactureRepository.findById(dto.getManufactureId())
                .orElseThrow(() -> new NotFoundException("Manufacture not found"));

        Computer entity = computerMapper.toEntity(dto, manufacture);
        if (imageFile != null && !imageFile.isEmpty()) {
            entity.setImage(imageFile.getBytes());
        }

        return computerMapper.toDto(computerRepository.save(entity));
    }

    public ComputerResponseDto updateComputer(Long id, ComputerRequestDto dto, MultipartFile imageFile) throws IOException {
    	
        Computer computer = computerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Computer not found"));


        if (imageFile != null && !imageFile.isEmpty()) {
            computer.setImage(imageFile.getBytes());
        }

        computerMapper.updateEntity(dto, computer);

        if (dto.getManufactureId() != null) {
            Manufacture manufacture = manufactureRepository.findById(dto.getManufactureId())
                    .orElseThrow(() -> new NotFoundException("Manufacture không tồn tại"));
            
            computer.setManufacture(manufacture);
        }
        return computerMapper.toDto(computerRepository.save(computer));
    }

    public void deleteComputer(Long id) {
        if (!computerRepository.existsById(id)) {
            throw new NotFoundException("Computer not found");
        }
        computerRepository.deleteById(id);
    }
}