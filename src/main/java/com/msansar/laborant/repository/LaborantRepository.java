package com.msansar.laborant.repository;

import com.msansar.laborant.model.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaborantRepository extends JpaRepository<Laborant, String> {
    Optional<Laborant> findLaborantByAdAndSoyad(String hastaAd, String hastaSoyad);
}
