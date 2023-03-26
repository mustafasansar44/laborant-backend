package com.msansar.laborant.dto;

import com.msansar.laborant.model.Tani;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaporDto {
    private String dosyaNo;
    private String hastaAd;
    private String hastaSoyad;
    private String hastaTC;
    private String taniBasligi;
    private String taniAciklamasi;
    private LocalDateTime raporVerilmeTarihi;
    private String raporFotografAdi;
}
