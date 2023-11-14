package com.sergio.jwt.backend.controllers;

import com.sergio.jwt.backend.dtos.CategoryDto;
import com.sergio.jwt.backend.dtos.GroupDto;
import com.sergio.jwt.backend.entites.CategoryEntity;
import com.sergio.jwt.backend.entites.GroupEntity;
import com.sergio.jwt.backend.mappers.CategoryMapper;
import com.sergio.jwt.backend.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService service;

    private final CategoryMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getFindById(@PathVariable Long id) {
        CategoryEntity categoryEntity = service.fetchOrFail(id);
        if (categoryEntity != null) {
            return new ResponseEntity<>(mapper.entityToDto(categoryEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getList() {
        List<CategoryEntity> categoryEntities = service.list();
        if (!categoryEntities.isEmpty()) {
            List<CategoryDto> categoryDtos = new ArrayList<>();
            for (CategoryEntity entity : categoryEntities) {
                categoryDtos.add(mapper.entityToDto(entity));
            }
            return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        CategoryEntity categoryEntity = service.save(mapper.dtoToEntity(categoryDto));
        return new ResponseEntity<>(mapper.entityToDto(categoryEntity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        CategoryEntity existingCategory = service.fetchOrFail(id);
        if (existingCategory != null) {
            categoryDto.setId(id);
            CategoryEntity updatedCategory = service.save(mapper.dtoToEntity(categoryDto));
            return new ResponseEntity<>(mapper.entityToDto(updatedCategory), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        CategoryEntity existingCategory = service.fetchOrFail(id);
        if (existingCategory != null) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
