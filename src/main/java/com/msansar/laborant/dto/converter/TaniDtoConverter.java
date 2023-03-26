package com.msansar.laborant.dto.converter;

import com.msansar.laborant.dto.TaniDto;
import com.msansar.laborant.model.Tani;
import org.springframework.stereotype.Component;

@Component
public class TaniDtoConverter {

    public TaniDto convertTaniToTaniDto(Tani tani){
        return new TaniDto(
                tani.name(),
                tani.getBaslik(),
                tani.getAciklama()
        );
    }
}
