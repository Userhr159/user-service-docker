package com.example.User_ServiceDocker.Services;

import com.example.User_ServiceDocker.Models.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Profile createProfile(Profile profile);
    List<Profile> getAllProfiles();
    Optional<Profile> getProfileById(Long id);
    Profile updateProfile(Long id, Profile profileDetails);
    void deleteProfile(Long id);
}
