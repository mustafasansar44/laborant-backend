package com.msansar.laborant.repository;

import com.msansar.laborant.model.Rapor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RaporRepository extends JpaRepository<Rapor, String> {

    Optional<Rapor> findRaporByHastaAdAndHastaSoyad(String hastaAd, String hastaSoyad);
}
