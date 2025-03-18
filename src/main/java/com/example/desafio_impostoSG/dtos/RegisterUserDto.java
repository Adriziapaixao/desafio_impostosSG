package com.example.desafio_impostoSG.dtos;


import lombok.Data;


@Data
public class RegisterUserDto {

    private String username;
    private String password;
    private RoleType role;

}
