package com.example.desafio_impostoSG.services;

import com.example.desafio_impostoSG.controllers.dtos.LoginDto;
import com.example.desafio_impostoSG.controllers.dtos.RegisterUserDto;
import com.example.desafio_impostoSG.controllers.dtos.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

        UserResponseDto createUser(RegisterUserDto registerUserDto);

        String login(LoginDto loginDto);
}
