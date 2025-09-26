package com.example.User_ServiceDocker.Mappers;

import com.example.User_ServiceDocker.Dtos.Users.UserRequestDTO;
import com.example.User_ServiceDocker.Dtos.Users.UserResponseDTO;
import com.example.User_ServiceDocker.Models.Role;
import com.example.User_ServiceDocker.Models.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setActive(user.getActive());
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles()
                    .stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static User toEntity(UserRequestDTO dto, Set<Role> roles) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setActive(dto.getActive() != null ? dto.getActive() : true);
        user.setRoles(roles);
        return user;
    }
}