package com.msansar.laborant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LaborantWithoutRaporDto {

    private String id;
    @NotBlank
    private String ad;
    @NotBlank
    private String soyad;
    @Size(min = 7, max = 7, message = "Hastane kimlik numarası 7 haneli olmalı!")
    private String hastaneKimlik;

}
