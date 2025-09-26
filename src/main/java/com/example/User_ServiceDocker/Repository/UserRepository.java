package com.example.User_ServiceDocker.Repository;

import com.example.User_ServiceDocker.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Puedes agregar métodos de búsqueda personalizados, por ejemplo:
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}