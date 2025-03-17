package com.example.securitySG.controllers.dtos;

import com.example.securitySG.models.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private RoleEntity role;
}
