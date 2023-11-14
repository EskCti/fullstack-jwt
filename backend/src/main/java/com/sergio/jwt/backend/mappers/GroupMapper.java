package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.GroupDto;
import com.sergio.jwt.backend.entites.GroupEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDto entityToDto(GroupEntity entity);

    GroupEntity dtoToEntity(GroupDto dto);
}
