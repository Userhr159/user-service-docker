package com.example.User_ServiceDocker.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Relación inversa con Users
    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;
}