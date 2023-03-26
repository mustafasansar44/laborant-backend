package com.msansar.laborant.service;

import com.msansar.laborant.dto.*;
import com.msansar.laborant.dto.converter.RaporDtoConverter;
import com.msansar.laborant.dto.converter.RaporLaborantDtoConverter;
import com.msansar.laborant.dto.converter.TaniDtoConverter;
import com.msansar.laborant.exception.FileCouldNotBeDeletedException;
import com.msansar.laborant.exception.NotFoundException;
import com.msansar.laborant.model.Laborant;
import com.msansar.laborant.model.Rapor;
import com.msansar.laborant.model.Tani;
import com.msansar.laborant.repository.RaporRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
public class RaporService {

    private final RaporRepository raporRepository;
    private final LaborantService laborantService;
    private final RaporDtoConverter raporDtoConverter;
    private final ImageService imageService;
    private final RaporLaborantDtoConverter raporLaborantDtoConverter;
    private final TaniDtoConverter taniDtoConverter;


    public RaporService(RaporRepository raporRepository,
                        LaborantService laborantService,
                        RaporDtoConverter raporDtoConverter,
                        ImageService imageService,
                        RaporLaborantDtoConverter raporLaborantDtoConverter,
                        TaniDtoConverter taniDtoConverter) {
        this.raporRepository = raporRepository;
        this.laborantService = laborantService;
        this.raporDtoConverter = raporDtoConverter;
        this.imageService = imageService;
        this.raporLaborantDtoConverter = raporLaborantDtoConverter;
        this.taniDtoConverter = taniDtoConverter;
    }



    protected Rapor findById(String id){
        Rapor rapor = raporRepository.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li rapor bulunamadı!"));
        return rapor;
    }

    public RaporDto save(RaporSaveRequestDto raporSaveRequestDto) throws IOException {
        Laborant laborant = laborantService.findByLaborantId(raporSaveRequestDto.getLaborant_id());
        String fileName = imageService.saveImage(raporSaveRequestDto.getRaporFotografi());

        Rapor rapor = new Rapor(
                raporSaveRequestDto.getHastaAd(),
                raporSaveRequestDto.getHastaSoyad(),
                raporSaveRequestDto.getHastaTC(),
                raporSaveRequestDto.getTani(),
                raporSaveRequestDto.getRaporVerilmeTarihi(),
                fileName,
                laborant
        );

        raporRepository.save(rapor);
        return raporDtoConverter.convertRaporToRaporDto(rapor);
    }

    public RaporDto getRaporById(String id){
        return raporDtoConverter.convertRaporToRaporDto(findById(id));
    }

    public RaporDto getRaporByHastaAdAndHastaSoyad(String hastaAd, String hastaSoyad){
        Rapor rapor = raporRepository.findRaporByHastaAdAndHastaSoyad(hastaAd, hastaSoyad).orElseThrow(
                () -> new NotFoundException(hastaAd + " " + hastaSoyad + " ,böyle bir hasta bulunamadı!"));
        return raporDtoConverter.convertRaporToRaporDto(rapor);
    }

    public List<RaporDto> getAll(){
        return raporRepository.findAll().stream().map(rapor ->
                raporDtoConverter.convertRaporToRaporDto(rapor)
        ).toList();
    }

    public RaporDto update(String id, RaporUpdateRequestDto raporUpdateRequestDto) throws IOException {
        Rapor rapor = findById(id);
        imageService.deleteImage(rapor.getRaporFotografAdi()); // eski resmi sil
        rapor.setRaporFotografAdi(imageService.saveImage(raporUpdateRequestDto.getRaporFotografi())); // rapor resmini değiştir
        rapor.setHastaAd(raporUpdateRequestDto.getHastaAd());
        rapor.setHastaSoyad(raporUpdateRequestDto.getHastaSoyad());
        rapor.setHastaTC(raporUpdateRequestDto.getHastaTC());
        rapor.setTani(raporUpdateRequestDto.getTani());
        rapor.setRaporVerilmeTarihi(raporUpdateRequestDto.getRaporVerilmeTarihi());

        raporRepository.save(rapor);
        return raporDtoConverter.convertRaporToRaporDto(rapor);
    }

    public String delete(String id) throws FileCouldNotBeDeletedException {
        Rapor rapor = findById(id);
        raporRepository.deleteById(id);
        imageService.deleteImage(rapor.getRaporFotografAdi());
        return "Rapor silindi!";
    }

    public RaporDetailsResponseDto getRaporDetailsById(String id){
        Rapor rapor = findById(id);
        return raporLaborantDtoConverter.convertRaporToRaporDetailsDto(rapor);
    }

    public List<TaniDto> getTani(){
        return Arrays.stream(Tani.values()).map(
                tani -> taniDtoConverter.convertTaniToTaniDto(tani)
        ).toList();
    }

}
