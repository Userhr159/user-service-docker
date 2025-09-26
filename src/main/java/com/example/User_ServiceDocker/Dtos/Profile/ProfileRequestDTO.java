package com.example.User_ServiceDocker.Dtos.Profile;

import lombok.Data;

@Data
public class ProfileRequestDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String bio;
    private Long userId; // para relacionar con un usuario
}
