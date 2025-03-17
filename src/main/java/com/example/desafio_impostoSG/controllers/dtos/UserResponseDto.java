package com.example.desafio_impostoSG.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private RoleType role;

    public UserResponseDto() {

    }
}
