package com.example.desafio_impostoSG.services;

import com.example.desafio_impostoSG.dtos.LoginDto;
import com.example.desafio_impostoSG.dtos.RegisterUserDto;
import com.example.desafio_impostoSG.dtos.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

        UserResponseDto createUser(RegisterUserDto registerUserDto);

        String login(LoginDto loginDto);
}
