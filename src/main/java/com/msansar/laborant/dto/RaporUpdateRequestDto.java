package com.msansar.laborant.dto;

import com.msansar.laborant.model.Tani;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaporUpdateRequestDto {
    @NotBlank
    private String hastaAd;
    @NotBlank
    private String hastaSoyad;
    @Size(min = 11, max = 11, message = "TC Kimlik numaranız 11 haneden oluşmalıdır!")
    private String hastaTC;
    @NotBlank
    private Tani tani;
    @NotBlank
    private LocalDateTime raporVerilmeTarihi;
    MultipartFile raporFotografi;
}
