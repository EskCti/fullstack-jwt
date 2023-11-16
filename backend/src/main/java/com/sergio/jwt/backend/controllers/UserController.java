package com.sergio.jwt.backend.controllers;

import com.sergio.jwt.backend.dtos.UserDto;
import com.sergio.jwt.backend.entites.GroupEntity;
import com.sergio.jwt.backend.entites.UserEntity;
import com.sergio.jwt.backend.mappers.UserMapper;
import com.sergio.jwt.backend.services.GroupService;
import com.sergio.jwt.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final GroupService groupService;

    private final UserMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserEntity userEntity = service.fetchOrFail(id);
        if (userEntity != null) {
            return new ResponseEntity<>(mapper.entityToDto(userEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getList() {
        List<UserEntity> userEntities = service.list();
        if (!userEntities.isEmpty()) {
            List<UserDto> userDtos = new ArrayList<>();
            for (UserEntity entity : userEntities) {
                userDtos.add(mapper.entityToDto(entity));
            }
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = service.save(mapper.dtoToEntity(userDto));
        return new ResponseEntity<>(mapper.entityToDto(userEntity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserEntity existingUser = service.fetchOrFail(id);
        if (existingUser != null) {
            userDto.setId(id);
            UserEntity updatedUser = service.save(mapper.dtoToEntity(userDto));
            return new ResponseEntity<>(mapper.entityToDto(updatedUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        UserEntity existingUser = service.fetchOrFail(id);
        if (existingUser != null) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/groups")
    public ResponseEntity<UserDto> associar(@PathVariable Long id, @RequestBody List<Long> idsGroups) {
        UserEntity existingUser = service.fetchOrFail(id);
        if (existingUser != null) {
            for (Long idGroup : idsGroups) {
                GroupEntity existingGroup = groupService.fetchOrFail(idGroup);
                if (existingGroup != null) {
                    existingUser.addGroup(existingGroup);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            UserEntity updatedUser = service.save(existingUser);
            return new ResponseEntity<>(mapper.entityToDto(existingUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
