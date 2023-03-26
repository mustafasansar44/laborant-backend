package com.msansar.laborant.dto.converter;

import com.msansar.laborant.dto.RaporDto;
import com.msansar.laborant.model.Rapor;
import org.springframework.stereotype.Component;

@Component
public class RaporDtoConverter {


    public RaporDto convertRaporToRaporDto(Rapor rapor){
        return new RaporDto(
                rapor.getDosyaNo(),
                rapor.getHastaAd(),
                rapor.getHastaSoyad(),
                rapor.getHastaTC(),
                rapor.getTani().getBaslik(),
                rapor.getTani().getAciklama(),
                rapor.getRaporVerilmeTarihi(),
                rapor.getRaporFotografAdi()
        );
    }



}
/*







*/