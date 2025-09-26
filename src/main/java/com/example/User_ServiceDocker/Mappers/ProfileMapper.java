package com.example.User_ServiceDocker.Mappers;

import com.example.User_ServiceDocker.Dtos.Profile.ProfileRequestDTO;
import com.example.User_ServiceDocker.Dtos.Profile.ProfileResponseDTO;
import com.example.User_ServiceDocker.Models.Profile;
import com.example.User_ServiceDocker.Models.User;

public class ProfileMapper {

    public static ProfileResponseDTO toDTO(Profile profile) {
        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(profile.getId());
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setAddress(profile.getAddress());
        dto.setBio(profile.getBio());
        if (profile.getUser() != null) {
            dto.setUserId(profile.getUser().getId());
        }
        return dto;
    }

    public static Profile toEntity(ProfileRequestDTO dto, User user) {
        Profile profile = new Profile();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setAddress(dto.getAddress());
        profile.setBio(dto.getBio());
        profile.setUser(user);
        return profile;
    }
}
