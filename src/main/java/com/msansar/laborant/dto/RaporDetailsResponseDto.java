package com.msansar.laborant.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaporDetailsResponseDto {

    private String dosyaNo;
    private String hastaAd;
    private String hastaSoyad;
    private String hastaTC;
    private String taniBasligi;
    private String taniAciklamasi;
    private LocalDateTime raporVerilmeTarihi;
    private String raporFotografAdi;
    private LaborantWithoutRaporDto laborantWithoutRaporDto;
}
