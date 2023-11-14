package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.CategoryDto;
import com.sergio.jwt.backend.entites.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto entityToDto(CategoryEntity entity);

    CategoryEntity dtoToEntity(CategoryDto dto);
}
