package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.PermissionDto;
import com.sergio.jwt.backend.entites.PermissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDto entityToDto(PermissionEntity entity);

    PermissionEntity dtoToEntity(PermissionDto dto);
}
