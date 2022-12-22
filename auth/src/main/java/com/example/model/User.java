package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_login", nullable = false)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

    
}