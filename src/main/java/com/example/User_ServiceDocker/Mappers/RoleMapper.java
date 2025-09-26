package com.example.User_ServiceDocker.Mappers;

import com.example.User_ServiceDocker.Dtos.Role.RoleRequestDTO;
import com.example.User_ServiceDocker.Dtos.Role.RoleResponseDTO;
import com.example.User_ServiceDocker.Models.Role;

public class RoleMapper {

    public static RoleResponseDTO toDTO(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }

    public static Role toEntity(RoleRequestDTO dto) {
        Role role = new Role();
        role.setName(dto.getName());
        return role;
    }
}