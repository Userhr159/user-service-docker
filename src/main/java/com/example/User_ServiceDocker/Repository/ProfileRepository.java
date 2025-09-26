package com.example.User_ServiceDocker.Repository;

import com.example.User_ServiceDocker.Models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
