package com.msansar.laborant.dto.converter;

import com.msansar.laborant.dto.LaborantDto;
import com.msansar.laborant.dto.LaborantWithoutRaporDto;
import com.msansar.laborant.model.Laborant;
import org.springframework.stereotype.Component;

@Component
public class LaborantDtoConverter {

    private final RaporDtoConverter raporDtoConverter;

    public LaborantDtoConverter(RaporDtoConverter raporDtoConverter) {
        this.raporDtoConverter = raporDtoConverter;
    }

    public LaborantDto convertLaborantToLaborantAndRaporDto(Laborant laborant){
        return new LaborantDto(
                laborant.getId(),
                laborant.getAd(),
                laborant.getSoyad(),
                laborant.getHastaneKimlik(),
                laborant.getRaporList().stream().map(raporDtoConverter::convertRaporToRaporDto).toList()
        );
    }

    public LaborantWithoutRaporDto convertLaborantToLaborantWithoutRaporDto(Laborant laborant){
        return new LaborantWithoutRaporDto(
                laborant.getId(),
                laborant.getAd(),
                laborant.getSoyad(),
                laborant.getHastaneKimlik()
        );
    }






}
