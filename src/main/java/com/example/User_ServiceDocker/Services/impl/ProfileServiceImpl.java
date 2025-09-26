package com.example.User_ServiceDocker.Services.impl;

import com.example.User_ServiceDocker.Models.Profile;
import com.example.User_ServiceDocker.Repository.ProfileRepository;
import com.example.User_ServiceDocker.Services.ProfileService;

import java.util.List;
import java.util.Optional;

public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    @Override
    public Profile updateProfile(Long id, Profile profileDetails) {
        return profileRepository.findById(id).map(profile -> {
            profile.setFirstName(profileDetails.getFirstName());
            profile.setLastName(profileDetails.getLastName());
            profile.setAddress(profileDetails.getAddress());
            profile.setBio(profileDetails.getBio());
            return profileRepository.save(profile);
        }).orElseThrow(() -> new RuntimeException("Perfil no encontrado con id " + id));
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }



}
