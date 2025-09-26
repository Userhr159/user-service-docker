package com.example.User_ServiceDocker.Controllers;


import com.example.User_ServiceDocker.Dtos.Profile.ProfileRequestDTO;
import com.example.User_ServiceDocker.Dtos.Profile.ProfileResponseDTO;
import com.example.User_ServiceDocker.Mappers.ProfileMapper;
import com.example.User_ServiceDocker.Models.Profile;
import com.example.User_ServiceDocker.Models.User;
import com.example.User_ServiceDocker.Services.ProfileService;
import com.example.User_ServiceDocker.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody ProfileRequestDTO dto) {
        User user = userService.getUserById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.getUserId()));
        Profile profile = ProfileMapper.toEntity(dto, user);
        Profile created = profileService.createProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProfileMapper.toDTO(created));
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() {
        List<ProfileResponseDTO> list = profileService.getAllProfiles()
                .stream()
                .map(ProfileMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id)
                .map(ProfileMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable Long id,
            @RequestBody ProfileRequestDTO dto
    ) {
        User user = userService.getUserById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.getUserId()));
        Profile profileToUpdate = ProfileMapper.toEntity(dto, user);
        Profile updated = profileService.updateProfile(id, profileToUpdate);
        return ResponseEntity.ok(ProfileMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
