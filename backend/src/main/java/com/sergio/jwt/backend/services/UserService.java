package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.CredentialsDto;
import com.sergio.jwt.backend.dtos.UserDto;
import com.sergio.jwt.backend.entites.UserEntity;
import com.sergio.jwt.backend.exceptions.AppException;
import com.sergio.jwt.backend.mappers.UserMapper;
import com.sergio.jwt.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService extends BaseService<UserEntity, UserRepository>{

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        UserEntity user = repository.findByCpfCnpj(credentialsDto.cpfCnpj())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.entityToDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(UserDto userDto) {
        System.out.println("*************** user DTO **********************");
        System.out.println(userDto);

        Optional<UserEntity> optionalUser = repository.findByCpfCnpj(userDto.cpfCnpj());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = userMapper.dtoToEntity(userDto);
        System.out.println("*************** user Obj **********************");
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        UserEntity savedUser = repository.save(user);

        return userMapper.entityToDto(savedUser);
    }

//    public UserDto findByLogin(String login) {
//        User user = userRepository.findByLogin(login)
//                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
//        return userMapper.toUserDto(user);
//    }

    public UserDto findByCpfCnpj(String cpfCnpj) {
        UserEntity user = repository.findByCpfCnpj(cpfCnpj)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.entityToDto(user);
    }

    public List<UserDto> findAll() {
        List<UserEntity> userEntities = repository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity user : userEntities) {
            userDtos.add(userMapper.entityToDto(user));
        }
        return userDtos;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        repository.flush();
    }

    @Override
    public UserEntity fetchOrFail(Long id) {
        var result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public List<UserEntity> list() {
        return repository.findAll();
    }
}
