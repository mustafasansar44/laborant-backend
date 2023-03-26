package com.msansar.laborant.config;

import com.msansar.laborant.dto.LaborantDto;
import com.msansar.laborant.dto.LaborantSaveRequestDto;
import com.msansar.laborant.dto.RaporSaveRequestDto;
import com.msansar.laborant.dto.UserSaveRequestDto;
import com.msansar.laborant.model.Laborant;
import com.msansar.laborant.model.Role;
import com.msansar.laborant.model.Tani;
import com.msansar.laborant.service.UserService;
import com.msansar.laborant.service.LaborantService;
import com.msansar.laborant.service.RaporService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StartupConfig implements CommandLineRunner {

    private final UserService userService;
    private final LaborantService laborantService;
    private final RaporService raporService;

    public StartupConfig(UserService userService, LaborantService laborantService, RaporService raporService) {
        this.userService = userService;
        this.laborantService = laborantService;
        this.raporService = raporService;
    }

    @Override
    public void run(String... args) throws Exception {

        userService.save(new UserSaveRequestDto("admin", "password", Role.ADMIN));
        userService.save(new UserSaveRequestDto("user", "password", Role.USER));
        LaborantSaveRequestDto laborantSaveRequestDto1 = new LaborantSaveRequestDto("Laborant1", "Soyad", "24132524231");
        LaborantSaveRequestDto laborantSaveRequestDto2 = new LaborantSaveRequestDto("Laborant2", "Soyad", "24242424224");
        LaborantSaveRequestDto laborantSaveRequestDto3 = new LaborantSaveRequestDto("Laborant3", "Soyad", "24242424224");
        laborantService.save(laborantSaveRequestDto1);
        laborantService.save(laborantSaveRequestDto2);
        laborantService.save(laborantSaveRequestDto3);

    }
}
