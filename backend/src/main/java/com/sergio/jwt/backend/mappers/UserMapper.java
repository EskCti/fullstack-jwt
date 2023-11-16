package com.sergio.jwt.backend.mappers;

import com.sergio.jwt.backend.dtos.UserDto;
import com.sergio.jwt.backend.entites.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto entityToDto(UserEntity user);

    @Mapping(target = "password", ignore = true)
    UserEntity dtoToEntity(UserDto userDto);

}
