package com.example.desafio_impostoSG.infra.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private AuthenticationConfiguration configuration;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Mock
    private  JwtAuthenticationFilter authenticationFilter;

    @InjectMocks
    private SecurityConfig securityConfig;


    /*
     * given_método_when_cenário_then_retornoEsperado
     */

    @Test
    void givenPasswordEncoderMethod_whenCalled_thenReturnsValidPasswordEncoder() {

        PasswordEncoder passwordEncoder = SecurityConfig.passwordEncoder();

        assertNotNull(passwordEncoder, "PasswordEncoder não deve ser nulo");

        String rawPassword = "senha123";
        String encodePassword = passwordEncoder.encode(rawPassword);

        assertNotNull(encodePassword, "Encode password não deve ser nulo");
        assertTrue(passwordEncoder.matches(rawPassword, encodePassword), "A senha codificada deve corresponder à senha original");
    }

    @Test
    void given_filterChainMethod_when_configuredWithHttpSecurity_then_returnsSecurityFilterChain() throws Exception {

        HttpSecurity httpSecurity = mock(HttpSecurity.class);
        DefaultSecurityFilterChain mockFilterChain = mock(DefaultSecurityFilterChain.class);

        when(httpSecurity.csrf(any())).thenReturn(httpSecurity);
        when(httpSecurity.authorizeHttpRequests(any())).thenReturn(httpSecurity);
        when(httpSecurity.httpBasic(any())).thenReturn(httpSecurity);
        when(httpSecurity.sessionManagement(any())).thenReturn(httpSecurity);
        when(httpSecurity.addFilterBefore(any(), any())).thenReturn(httpSecurity);
        when(httpSecurity.build()).thenReturn(mockFilterChain);

        SecurityFilterChain securityFilterChain = securityConfig.filterChain(httpSecurity);


        assertThat(securityFilterChain).isNotNull();
    }


}