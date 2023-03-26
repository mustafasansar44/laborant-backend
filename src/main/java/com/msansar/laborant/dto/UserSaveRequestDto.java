package com.msansar.laborant.dto;


import com.msansar.laborant.model.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveRequestDto {
    private String username;
    private String password;
    private Role role;
}
