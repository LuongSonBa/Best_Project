package com.example.login.controller;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.login.dto.ComputerRequestDto;
import com.example.login.dto.ComputerResponseDto;
import com.example.login.service.ComputerService;

@RestController
@RequestMapping("/api/computers")
public class ComputerController {

    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getComputerImage(@PathVariable Long id) {
        byte[] image = computerService.getComputerImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ComputerResponseDto> createComputer(
            @RequestPart("data") ComputerRequestDto dto,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {
    	System.out.println("abc");
        return ResponseEntity.ok(computerService.createComputer(dto, imageFile));
        
    }
    
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ComputerResponseDto> updateComputer(
            @PathVariable Long id,
            @RequestPart("data") ComputerRequestDto dto,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {
        return ResponseEntity.ok(computerService.updateComputer(id, dto, imageFile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComputer(@PathVariable Long id) {
        computerService.deleteComputer(id);
        return ResponseEntity.noContent().build();
    }
}