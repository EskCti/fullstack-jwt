package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
