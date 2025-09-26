package com.example.User_ServiceDocker.Controllers;

import com.example.User_ServiceDocker.Dtos.Users.UserRequestDTO;
import com.example.User_ServiceDocker.Dtos.Users.UserResponseDTO;
import com.example.User_ServiceDocker.Mappers.UserMapper;
import com.example.User_ServiceDocker.Models.Role;
import com.example.User_ServiceDocker.Models.User;
import com.example.User_ServiceDocker.Services.RoleService;
import com.example.User_ServiceDocker.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // Helper: resuelve Set<Role> a partir de IDs
    private Set<Role> resolveRolesFromIds(Set<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) return Collections.emptySet();

        return roleIds.stream()
                .map(id -> roleService.getRoleById(id)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado con id " + id)))
                .collect(Collectors.toSet());
    }

    // Crear usuario (recibe DTO)
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto) {
        Set<Role> roles = resolveRolesFromIds(dto.getRoleIds());
        User user = UserMapper.toEntity(dto, roles);
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDTO(created));
    }

    // Listar usuarios (devuelve DTOs)
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> list = userService.getAllUsers()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(UserMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar por email
    @GetMapping("/search/email")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(UserMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar por username
    @GetMapping("/search/username")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username)
                .map(UserMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar usuario (recibe DTO)
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO dto
    ) {
        Set<Role> roles = resolveRolesFromIds(dto.getRoleIds());
        // Creamos entidad con los campos del DTO (el service.updateUser reemplazará campos según implementación)
        User userToUpdate = UserMapper.toEntity(dto, roles);
        User updated = userService.updateUser(id, userToUpdate);
        return ResponseEntity.ok(UserMapper.toDTO(updated));
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}