package com.msansar.laborant.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "laborant")
public class Laborant{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String ad;
    private String soyad;
    private String hastaneKimlik;

    @OneToMany(mappedBy = "laborant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rapor> raporList;

    public Laborant(String ad, String soyad, String hastaneKimlik) {
        this.ad = ad;
        this.soyad = soyad;
        this.hastaneKimlik = hastaneKimlik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Laborant laborant = (Laborant) o;

        if (!id.equals(laborant.id)) return false;
        if (!Objects.equals(ad, laborant.ad)) return false;
        if (!Objects.equals(soyad, laborant.soyad)) return false;
        return hastaneKimlik.equals(laborant.hastaneKimlik);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (ad != null ? ad.hashCode() : 0);
        result = 31 * result + (soyad != null ? soyad.hashCode() : 0);
        result = 31 * result + hastaneKimlik.hashCode();
        return result;
    }
}
