package com.msansar.laborant.controller;


import com.msansar.laborant.dto.LaborantDto;
import com.msansar.laborant.dto.LaborantSaveRequestDto;
import com.msansar.laborant.dto.RaporDto;
import com.msansar.laborant.dto.RaporSaveRequestDto;
import com.msansar.laborant.exception.FileCouldNotBeDeletedException;
import com.msansar.laborant.service.LaborantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/laborant")
public class LaborantController {

    private final LaborantService laborantService;

    public LaborantController(LaborantService laborantService) {
        this.laborantService = laborantService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody LaborantSaveRequestDto laborantSaveRequestDto) {
        return ResponseEntity.ok(laborantService.save(laborantSaveRequestDto));
    }

    @GetMapping("/getLaborantByNameAndSurname")
    public ResponseEntity<LaborantDto> getLaborantByNameAndSurname(@NotBlank @RequestParam String ad,@NotBlank @RequestParam String soyad){
        return ResponseEntity.ok(laborantService.getLaborantByAdAndSoyad(ad, soyad));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<LaborantDto>> getAll(){
        return ResponseEntity.ok(laborantService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok(laborantService.delete(id));
    }
}
