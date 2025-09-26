package com.example.User_ServiceDocker.Controllers;

import com.example.User_ServiceDocker.Dtos.Role.RoleRequestDTO;
import com.example.User_ServiceDocker.Dtos.Role.RoleResponseDTO;
import com.example.User_ServiceDocker.Mappers.RoleMapper;
import com.example.User_ServiceDocker.Models.Role;
import com.example.User_ServiceDocker.Services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Crear rol
    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO dto) {
        Role role = RoleMapper.toEntity(dto);
        Role created = roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(RoleMapper.toDTO(created));
    }

    // Listar todos los roles
    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        List<RoleResponseDTO> list = roleService.getAllRoles()
                .stream()
                .map(RoleMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(RoleMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar rol por nombre
    @GetMapping("/search")
    public ResponseEntity<RoleResponseDTO> getRoleByName(@RequestParam String name) {
        return roleService.getRoleByName(name)
                .map(RoleMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar rol
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(
            @PathVariable Long id,
            @RequestBody RoleRequestDTO dto
    ) {
        Role roleToUpdate = RoleMapper.toEntity(dto);
        Role updated = roleService.updateRole(id, roleToUpdate);
        return ResponseEntity.ok(RoleMapper.toDTO(updated));
    }

    // Eliminar rol
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
