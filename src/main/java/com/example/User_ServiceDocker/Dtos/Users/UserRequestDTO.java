package com.example.User_ServiceDocker.Dtos.Users;

import lombok.Data;

import java.util.Set;

@Data
public class UserRequestDTO {
    private String username;
    private String email;
    private String phone;
    private Boolean active;
    private Set<Long> roleIds; // sólo enviamos IDs de roles
}
