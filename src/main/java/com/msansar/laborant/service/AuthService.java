package com.msansar.laborant.service;

import com.msansar.laborant.dto.TokenResponseDto;
import com.msansar.laborant.dto.UserSaveRequestDto;
import com.msansar.laborant.exception.NotFoundException;
import com.msansar.laborant.model.User;
import com.msansar.laborant.utils.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, TokenGenerator tokenGenerator, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponseDto login(String username, String password){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            return new TokenResponseDto(tokenGenerator.generateToken(authentication));
        }catch (Exception exception){
            throw new NotFoundException("Kullanıcı bulunamadı!");
        }
    }



}
