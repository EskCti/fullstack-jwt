package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.dtos.CredentialsDto;
import com.sergio.jwt.backend.dtos.SignUpDto;
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
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        UserEntity user = userRepository.findByCpfCnpj(credentialsDto.cpfCnpj())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        System.out.println("*************** user DTO **********************");
        System.out.println(userDto);

        Optional<UserEntity> optionalUser = userRepository.findByCpfCnpj(userDto.cpfCnpj());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = userMapper.signUpToUser(userDto);
        System.out.println("*************** user Obj **********************");
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        UserEntity savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

//    public UserDto findByLogin(String login) {
//        User user = userRepository.findByLogin(login)
//                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
//        return userMapper.toUserDto(user);
//    }

    public UserDto findByCpfCnpj(String cpfCnpj) {
        UserEntity user = userRepository.findByCpfCnpj(cpfCnpj)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }
}
