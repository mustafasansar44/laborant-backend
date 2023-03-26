package com.msansar.laborant.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {   // Spring Security authentication test controller

    @GetMapping("/admin")   // Sadece admin erişebilir
    public String admin(){
        return "ADMIN";
    }
    @GetMapping("/user")    // user ve admin erişebilir
    public String user(){
        return "user";
    }
    @GetMapping("/public")
    public String publicapi(){
        return "public";
    }

}
