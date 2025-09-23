package com.example.User_ServiceDocker.Repository;

import com.example.User_ServiceDocker.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    // Puedes agregar métodos de búsqueda personalizados, por ejemplo:
    Users findByUsername(String username);
}