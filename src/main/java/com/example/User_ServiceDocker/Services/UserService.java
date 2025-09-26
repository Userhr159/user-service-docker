package com.example.User_ServiceDocker.Services;

import com.example.User_ServiceDocker.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByUsername(String username);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
}
