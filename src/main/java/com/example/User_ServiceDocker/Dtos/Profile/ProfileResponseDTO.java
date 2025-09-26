package com.example.User_ServiceDocker.Dtos.Profile;

import lombok.Data;

@Data
public class ProfileResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String bio;
    private Long userId; // solo el id del usuario
}