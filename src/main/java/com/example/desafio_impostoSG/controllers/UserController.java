package com.example.desafio_impostoSG.controllers;

import com.example.desafio_impostoSG.controllers.dtos.AuthResponseDto;
import com.example.desafio_impostoSG.controllers.dtos.LoginDto;
import com.example.desafio_impostoSG.controllers.dtos.RegisterUserDto;
import com.example.desafio_impostoSG.controllers.dtos.UserResponseDto;
import com.example.desafio_impostoSG.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody RegisterUserDto registerUserDto){

        UserResponseDto userResponseDto = userService.createUser(registerUserDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        String token = userService.login(loginDto);

        AuthResponseDto authResponse = AuthResponseDto.builder()
                .accessToken(token)
                .build();

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    // Tratamento de exceções de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}


