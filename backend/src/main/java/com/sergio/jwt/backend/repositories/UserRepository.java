package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    Optional<User> findByLogin(String login);
    Optional<UserEntity> findByCpfCnpj(String cpfCnpj);
}
