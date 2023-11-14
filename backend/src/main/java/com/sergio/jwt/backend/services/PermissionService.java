package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.entites.GroupEntity;
import com.sergio.jwt.backend.entites.PermissionEntity;
import com.sergio.jwt.backend.repositories.GroupRepository;
import com.sergio.jwt.backend.repositories.PermissionRepository;

public class PermissionService extends BaseService<PermissionEntity, PermissionRepository>{

    @Override
    public PermissionEntity save(PermissionEntity request) {
        request = super.repository.save(request);
        return request;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        repository.flush();
    }

    @Override
    public PermissionEntity fetchOrFail(Long id) {
        var result = repository.findById(id);
        return result.orElse(null);
    }
}
