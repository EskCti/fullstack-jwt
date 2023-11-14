package com.sergio.jwt.backend.controllers;

import com.sergio.jwt.backend.dtos.GroupDto;
import com.sergio.jwt.backend.entites.GroupEntity;
import com.sergio.jwt.backend.mappers.GroupMapper;
import com.sergio.jwt.backend.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/groups")
@AllArgsConstructor
public class GroupController {

    private final GroupService service;

    private final GroupMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long id) {
        GroupEntity groupEntity = service.fetchOrFail(id);
        if (groupEntity != null) {
            return new ResponseEntity<>(mapper.entityToDto(groupEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getList() {
        List<GroupEntity> groupEntities = service.list();
        if (!groupEntities.isEmpty()) {
            List<GroupDto> groupDtos = new ArrayList<>();
            for (GroupEntity entity : groupEntities) {
                groupDtos.add(mapper.entityToDto(entity));
            }
            return new ResponseEntity<>(groupDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        GroupEntity groupEntity = service.save(mapper.dtoToEntity(groupDto));
        return new ResponseEntity<>(mapper.entityToDto(groupEntity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long id, @RequestBody GroupDto groupDto) {
        GroupEntity existingGroup = service.fetchOrFail(id);
        if (existingGroup != null) {
            groupDto.setId(id);
            GroupEntity updatedGroup = service.save(mapper.dtoToEntity(groupDto));
            return new ResponseEntity<>(mapper.entityToDto(updatedGroup), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        GroupEntity existingGroup = service.fetchOrFail(id);
        if (existingGroup != null) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
