package com.msansar.laborant.service;

import com.msansar.laborant.dto.LaborantDto;
import com.msansar.laborant.dto.UserSaveRequestDto;
import com.msansar.laborant.exception.NotFoundException;
import com.msansar.laborant.model.Role;
import com.msansar.laborant.model.User;
import com.msansar.laborant.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String save(UserSaveRequestDto userSaveRequestDto){
        userRepository.save(new User(
                userSaveRequestDto.getUsername(),
                passwordEncoder.encode(userSaveRequestDto.getPassword()),
                userSaveRequestDto.getRole()
        ));
        return "Kullanıcı kaydedildi!";
    }

    protected User findUserByUsername(String username){
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new NotFoundException("Böyle bir yönetici yok!"));
        return user;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
