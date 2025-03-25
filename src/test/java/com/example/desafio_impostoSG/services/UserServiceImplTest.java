package com.example.desafio_impostoSG.services;

import com.example.desafio_impostoSG.dtos.LoginDto;
import com.example.desafio_impostoSG.dtos.RegisterUserDto;
import com.example.desafio_impostoSG.dtos.RoleType;
import com.example.desafio_impostoSG.dtos.UserResponseDto;
import com.example.desafio_impostoSG.infra.config.JwtTokenProvider;
import com.example.desafio_impostoSG.models.RoleEntity;
import com.example.desafio_impostoSG.models.UserEntity;
import com.example.desafio_impostoSG.repostories.RoleRepository;
import com.example.desafio_impostoSG.repostories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;


import static com.example.desafio_impostoSG.dtos.RoleType.ROLE_ADMIN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserServiceImpl userService;

    /*
     * given_método_when_cenário_then_retornoEsperado
     */

    @Test
    void given_existingUsername_when_createUser_then_throwException() {

        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setUsername("existingUser");
        userDto.setPassword("password123");
        userDto.setRole(ROLE_ADMIN);

        Mockito.when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> userService.createUser(userDto)
        );

        Assertions.assertEquals("Username já existe no banco de dados.", exception.getMessage());

        verify(userRepository).existsByUsername("existingUser");
    }

    @Test
    void givenExistingUsername_whenCreateUser_thenThrowException() {

        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername("existingUser");
        registerUserDto.setPassword("password123");
        registerUserDto.setRole(RoleType.ROLE_USER);

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);


        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(registerUserDto);
        });

        Assertions.assertEquals("Username já existe no banco de dados.", exception.getMessage());

        verify(userRepository).existsByUsername("existingUser");
        verifyNoMoreInteractions(userRepository, roleRepository, bCryptPasswordEncoder);
    }

    @Test
    void given_login_when_reaizarLogin_then_loginBemSucedido() {

        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("usuário");
        loginDto.setPassword("4321");

        Authentication authentication = mock(Authentication.class);
        String accessToken = "token";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn(accessToken);

        String tokenGerado = userService.login(loginDto);

        Assertions.assertNotNull(tokenGerado);
        Assertions.assertEquals(accessToken, tokenGerado);

    }
}