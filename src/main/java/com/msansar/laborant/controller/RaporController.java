package com.msansar.laborant.controller;


import com.msansar.laborant.dto.*;
import com.msansar.laborant.exception.FileCouldNotBeDeletedException;
import com.msansar.laborant.service.RaporService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rapor")
public class RaporController {

    private final RaporService raporService;

    public RaporController(RaporService raporService) {
        this.raporService = raporService;
    }

    @PostMapping("/save")
    public ResponseEntity<RaporDto> save(@ModelAttribute RaporSaveRequestDto raporSaveRequestDto) throws IOException {
        System.out.println("SSS");
        return ResponseEntity.ok(raporService.save(raporSaveRequestDto));
    }

    @GetMapping("/getReportById/{id}")
    public ResponseEntity<RaporDto> getReportById(@PathVariable String id){
        return ResponseEntity.ok(raporService.getRaporById(id));
    }

    @GetMapping("/getReportByNameAndSurname")
    public ResponseEntity<RaporDto> getReportByNameAndSurname(@RequestParam String name, @RequestParam String surname){
        return ResponseEntity.ok(raporService.getRaporByHastaAdAndHastaSoyad(name, surname));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RaporDto>> getAll(){
        return ResponseEntity.ok(raporService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RaporDto> update(@PathVariable String id,@Valid @RequestBody RaporUpdateRequestDto raporUpdateRequestDto) throws IOException {
        return ResponseEntity.ok(raporService.update(id, raporUpdateRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws FileCouldNotBeDeletedException {
        return ResponseEntity.ok(raporService.delete(id));
    }

    @GetMapping("/getRaporDetailsById/{id}")
    public ResponseEntity<RaporDetailsResponseDto> getRaporDetailsById(@PathVariable String id){
        return ResponseEntity.ok(raporService.getRaporDetailsById(id));
    }
    @GetMapping("/getTani")
    public ResponseEntity<List<TaniDto>> getTani(){
        return ResponseEntity.ok(raporService.getTani());
    }
}
