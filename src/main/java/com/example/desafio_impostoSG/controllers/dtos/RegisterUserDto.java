package com.example.desafio_impostoSG.controllers.dtos;


import com.example.desafio_impostoSG.models.RoleEntity;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


@Data
public class RegisterUserDto {

    private String username;
    private String password;
    private RoleType role;

}
