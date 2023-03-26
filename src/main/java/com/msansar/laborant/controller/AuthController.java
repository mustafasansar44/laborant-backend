package com.msansar.laborant.controller;


import com.msansar.laborant.dto.LaborantSaveRequestDto;
import com.msansar.laborant.dto.TokenResponseDto;
import com.msansar.laborant.dto.UserSaveRequestDto;
import com.msansar.laborant.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestParam String username, @RequestParam String password){
        return ResponseEntity.ok(authService.login(username, password));
    }
}
