package com.msansar.laborant.dto.converter;

import com.msansar.laborant.dto.RaporDetailsResponseDto;
import com.msansar.laborant.model.Rapor;
import org.springframework.stereotype.Component;

@Component
public class RaporLaborantDtoConverter {

    private final LaborantDtoConverter laborantDtoConverter;

    public RaporLaborantDtoConverter(LaborantDtoConverter laborantDtoConverter) {
        this.laborantDtoConverter = laborantDtoConverter;
    }

    public RaporDetailsResponseDto convertRaporToRaporDetailsDto(Rapor rapor){
        return new RaporDetailsResponseDto(
                rapor.getDosyaNo(),
                rapor.getHastaAd(),
                rapor.getHastaSoyad(),
                rapor.getHastaTC(),
                rapor.getTani().getBaslik(),
                rapor.getTani().getAciklama(),
                rapor.getRaporVerilmeTarihi(),
                rapor.getRaporFotografAdi(),
                laborantDtoConverter.convertLaborantToLaborantWithoutRaporDto(rapor.getLaborant())
        );
    }

}
