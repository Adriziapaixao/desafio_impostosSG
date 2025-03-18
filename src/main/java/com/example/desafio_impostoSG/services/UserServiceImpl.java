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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private final AuthenticationManager authenticationManager;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;

    }

    @Override
    public UserResponseDto createUser(RegisterUserDto registerUserDto) {

        // Verificar se o e-mail já existe
        if (userRepository.existsByUsername(registerUserDto.getUsername())) {
            throw new IllegalArgumentException("Username já existe no banco de dados.");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerUserDto.getPassword()));


        RoleType roleType = registerUserDto.getRole();
        RoleEntity role = new RoleEntity();
        role.setName(roleType.name());
        roleRepository.save(role);

        user.setRole(role);
        userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setRole(roleType);

        return userResponseDto;
    }


    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);

    }


    private String authenticateUser() {
        // Obtém o objeto Authentication do contexto de segurança
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Gera o token JWT
        return jwtTokenProvider.generateToken(authentication);
    }

}

