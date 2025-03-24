package com.example.desafio_impostoSG.services;

import com.example.desafio_impostoSG.models.RoleEntity;
import com.example.desafio_impostoSG.models.UserEntity;
import com.example.desafio_impostoSG.repostories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static com.example.desafio_impostoSG.dtos.RoleType.ROLE_USER;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDatailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDatailsService userDetailsService;

    @BeforeEach
    void setUp() {

    }

    /*
     * given_método_when_cenário_then_retornoEsperado
     */
    @Test
    void given_loadUserByUsername_when_usernameExists_then_returnsUserDetails() {

        String username = "UsuárioTest";

        RoleEntity role = new RoleEntity();
        role.setName("ROLE_USER");

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword("1234");
        user.setRole(role);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(username, userDetails.getUsername());
        Assertions.assertEquals("1234", userDetails.getPassword());
        Mockito.verify(userRepository).findByUsername(username);

    }
}