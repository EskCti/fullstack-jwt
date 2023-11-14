package com.sergio.jwt.backend.repositories;

import com.sergio.jwt.backend.entites.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
