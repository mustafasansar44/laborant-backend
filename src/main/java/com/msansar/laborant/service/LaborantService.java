package com.msansar.laborant.service;


import com.msansar.laborant.dto.LaborantDto;
import com.msansar.laborant.dto.LaborantSaveRequestDto;
import com.msansar.laborant.dto.converter.LaborantDtoConverter;
import com.msansar.laborant.exception.FileCouldNotBeDeletedException;
import com.msansar.laborant.exception.NotFoundException;
import com.msansar.laborant.model.Laborant;
import com.msansar.laborant.model.Rapor;
import com.msansar.laborant.repository.LaborantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaborantService {

    private final LaborantRepository laborantRepository;
    private final LaborantDtoConverter laborantDtoConverter;
    private final PasswordEncoder passwordEncoder;

    public LaborantService(LaborantRepository laborantRepository, LaborantDtoConverter laborantDtoConverter, PasswordEncoder passwordEncoder) {
        this.laborantRepository = laborantRepository;
        this.laborantDtoConverter = laborantDtoConverter;
        this.passwordEncoder = passwordEncoder;
    }

    protected Laborant findByLaborantId(String id){
        return laborantRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id + " id'ye sahip laborant bulunamadı!"));   // HATA DÖNDER
    }

    public String save(LaborantSaveRequestDto laborantSaveRequestDto){

        Laborant laborant = new Laborant(
                laborantSaveRequestDto.getAd(),
                laborantSaveRequestDto.getSoyad(),
                laborantSaveRequestDto.getHastaneKimlik()
        );
        laborantRepository.save(laborant);
        return "Laborant Kaydedildi!";
    }

    public LaborantDto getLaborantByAdAndSoyad(String ad, String soyad){
        Laborant laborant = laborantRepository.findLaborantByAdAndSoyad(ad, soyad)
                .orElseThrow(() -> new NotFoundException(ad + " " + soyad +" Böyle bir laborant bulunamadı!"));
        return laborantDtoConverter.convertLaborantToLaborantAndRaporDto(laborant);
    }

    public List<LaborantDto> getAll(){
        return laborantRepository.findAll().stream().map(laborant ->
                    laborantDtoConverter.convertLaborantToLaborantAndRaporDto(laborant)
                ).toList();
    }

    public String delete(String id) {
        laborantRepository.deleteById(findByLaborantId(id).getId());
        return "Rapor silindi!";
    }
}
