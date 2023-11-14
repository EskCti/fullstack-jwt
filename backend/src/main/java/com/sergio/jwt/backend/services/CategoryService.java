package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.entites.CategoryEntity;
import com.sergio.jwt.backend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseService<CategoryEntity, CategoryRepository>{

    @Override
    public CategoryEntity save(CategoryEntity request) {
        request = super.repository.save(request);
        return request;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        repository.flush();
    }

    @Override
    public CategoryEntity fetchOrFail(Long id) {
        var result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public List<CategoryEntity> list() {
        return repository.findAll();
    }
}
