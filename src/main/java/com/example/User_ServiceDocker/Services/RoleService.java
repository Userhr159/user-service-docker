package com.example.User_ServiceDocker.Services;

import com.example.User_ServiceDocker.Models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role createRole(Role role);
    List<Role> getAllRoles();
    Optional<Role> getRoleById(Long id);
    Optional<Role> getRoleByName(String name);
    Role updateRole(Long id, Role roleDetails);
    void deleteRole(Long id);
}
