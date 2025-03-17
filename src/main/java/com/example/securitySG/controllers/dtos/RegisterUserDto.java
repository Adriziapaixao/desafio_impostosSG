package com.example.securitySG.controllers.dtos;


import com.example.securitySG.models.RoleEntity;
import lombok.Data;

import java.util.Set;


@Data
public class RegisterUserDto {

    private String username;
    private String password;
    private Set<RoleEntity> roles;
}
