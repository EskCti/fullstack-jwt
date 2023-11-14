package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.entites.GroupEntity;
import com.sergio.jwt.backend.repositories.GroupRepository;

import java.util.List;

public class GroupService extends BaseService<GroupEntity, GroupRepository>{

    @Override
    public GroupEntity save(GroupEntity request) {
        request = super.repository.save(request);
        return request;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        repository.flush();
    }

    @Override
    public GroupEntity fetchOrFail(Long id) {
        var result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public List<GroupEntity> list() {
        return repository.findAll();
    }
}
