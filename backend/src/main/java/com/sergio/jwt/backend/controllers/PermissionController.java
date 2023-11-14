package com.sergio.jwt.backend.controllers;

import com.sergio.jwt.backend.dtos.PermissionDto;
import com.sergio.jwt.backend.entites.PermissionEntity;
import com.sergio.jwt.backend.mappers.PermissionMapper;
import com.sergio.jwt.backend.services.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@AllArgsConstructor
public class PermissionController {

    private final PermissionService service;

    private final PermissionMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> getFindById(@PathVariable Long id) {
        PermissionEntity permissionEntity = service.fetchOrFail(id);
        if (permissionEntity != null) {
            return new ResponseEntity<>(mapper.entityToDto(permissionEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PermissionDto>> getList() {
        List<PermissionEntity> permissionEntities = service.list();
        if (!permissionEntities.isEmpty()) {
            List<PermissionDto> permissionDtos = new ArrayList<>();
            for (PermissionEntity entity : permissionEntities) {
                permissionDtos.add(mapper.entityToDto(entity));
            }
            return new ResponseEntity<>(permissionDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PermissionDto> create(@RequestBody PermissionDto permissionDto) {
        PermissionEntity permissionEntity = service.save(mapper.dtoToEntity(permissionDto));
        return new ResponseEntity<>(mapper.entityToDto(permissionEntity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDto> update(@PathVariable Long id, @RequestBody PermissionDto permissionDto) {
        PermissionEntity existingPermission = service.fetchOrFail(id);
        if (existingPermission != null) {
            permissionDto.setId(id);
            PermissionEntity updatedPermission = service.save(mapper.dtoToEntity(permissionDto));
            return new ResponseEntity<>(mapper.entityToDto(updatedPermission), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        PermissionEntity existingPermission = service.fetchOrFail(id);
        if (existingPermission != null) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
