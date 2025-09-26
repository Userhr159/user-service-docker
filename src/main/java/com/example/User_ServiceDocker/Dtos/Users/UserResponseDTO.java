package com.example.User_ServiceDocker.Dtos.Users;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private Boolean active;
    private Set<String> roles; // nombres de roles
}