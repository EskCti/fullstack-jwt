package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
