package com.msansar.laborant.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rapor")
public class Rapor {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String dosyaNo;
    private String hastaAd;
    private String hastaSoyad;
    private String hastaTC;
    @Enumerated(EnumType.STRING)
    private Tani tani;
    private LocalDateTime raporVerilmeTarihi = LocalDateTime.now();
    private String raporFotografAdi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laborant_id", nullable = false)
    private Laborant laborant;

    public Rapor(String hastaAd, String hastaSoyad, String hastaTC, Tani tani, LocalDateTime raporVerilmeTarihi, String raporFotografAdi, Laborant laborant) {
        this.hastaAd = hastaAd;
        this.hastaSoyad = hastaSoyad;
        this.hastaTC = hastaTC;
        this.tani = tani;
        this.raporVerilmeTarihi = raporVerilmeTarihi;
        this.raporFotografAdi = raporFotografAdi;
        this.laborant = laborant;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rapor rapor = (Rapor) o;

        if (!dosyaNo.equals(rapor.dosyaNo)) return false;
        if (!Objects.equals(hastaAd, rapor.hastaAd)) return false;
        if (!Objects.equals(hastaSoyad, rapor.hastaSoyad)) return false;
        if (!hastaTC.equals(rapor.hastaTC)) return false;
        if (tani != rapor.tani) return false;
        if (!raporVerilmeTarihi.equals(rapor.raporVerilmeTarihi)) return false;
        if (!Objects.equals(raporFotografAdi, rapor.raporFotografAdi))
            return false;
        return Objects.equals(laborant, rapor.laborant);
    }

    @Override
    public int hashCode() {
        int result = dosyaNo.hashCode();
        result = 31 * result + (hastaAd != null ? hastaAd.hashCode() : 0);
        result = 31 * result + (hastaSoyad != null ? hastaSoyad.hashCode() : 0);
        result = 31 * result + hastaTC.hashCode();
        result = 31 * result + tani.hashCode();
        result = 31 * result + raporVerilmeTarihi.hashCode();
        result = 31 * result + (raporFotografAdi != null ? raporFotografAdi.hashCode() : 0);
        result = 31 * result + (laborant != null ? laborant.hashCode() : 0);
        return result;
    }
}