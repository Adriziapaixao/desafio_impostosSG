package com.example.desafio_impostoSG.controllers;

import com.example.desafio_impostoSG.dtos.AuthResponseDto;
import com.example.desafio_impostoSG.dtos.LoginDto;
import com.example.desafio_impostoSG.dtos.RegisterUserDto;
import com.example.desafio_impostoSG.dtos.UserResponseDto;
import com.example.desafio_impostoSG.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody RegisterUserDto registerUserDto){

        UserResponseDto userResponseDto = userService.createUser(registerUserDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        String token = userService.login(loginDto);

        AuthResponseDto authResponse = AuthResponseDto.builder()
                .accessToken(token)
                .build();

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}


