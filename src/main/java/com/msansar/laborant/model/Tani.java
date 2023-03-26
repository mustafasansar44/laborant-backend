package com.msansar.laborant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tani {
    AKCIGER_KANSERI("Akciğer Kanseri","Akciğer kanseri açıklaması"),
    SOGUK_ALGINLIGI("Soğuk Algınlığı","Soğuk algınlığı açıklaması"),
    GRIP("Grip", "Grip açıklaması");

    String baslik;
    String aciklama;

}
