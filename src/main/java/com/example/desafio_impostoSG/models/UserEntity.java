package com.example.desafio_impostoSG.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;


    public UserEntity(String username) {
    }

}



