package com.msansar.laborant.controller;

import com.msansar.laborant.dto.LaborantDto;
import com.msansar.laborant.dto.UserSaveRequestDto;
import com.msansar.laborant.model.User;
import com.msansar.laborant.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save( @RequestBody UserSaveRequestDto userSaveRequestDto) {
        return ResponseEntity.ok(userService.save(userSaveRequestDto));
    }
    // normalde User dönmem ama bu sadece login için kendim development sürecinde kullanacagımdan dolayı böyle
    // production'da buranın dto olması esneklik ve güvenlik sağlar.
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
}
