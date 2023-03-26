package com.msansar.laborant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LaborantDto {

    private String id;
    @NotBlank
    private String ad;
    @NotBlank
    private String soyad;
    @NotBlank
    @Size(min = 7, max = 7, message = "Hastane kimlik numarası 7 haneli olmalı!")
    private String hastaneKimlik;
    private List<RaporDto> raporList;

    public LaborantDto(String id, String ad, String soyad, String hastaneKimlik) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.hastaneKimlik = hastaneKimlik;
    }
}
