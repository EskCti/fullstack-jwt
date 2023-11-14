package com.sergio.jwt.backend.services;

import com.sergio.jwt.backend.entites.BaseEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T extends BaseEntity, R extends JpaRepository<T, Long>> {
	
	@Autowired
	protected R repository;
					
	@Transactional
	public T save(T t) {
		return repository.save(t);
	}
	
	@Transactional
	public abstract void delete(Long id);
	
	public abstract T fetchOrFail(Long id);
}
