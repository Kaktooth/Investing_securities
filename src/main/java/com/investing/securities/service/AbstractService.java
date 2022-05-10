package com.investing.securities.service;

import com.investing.securities.exception.ResourceNotFoundException;
import com.investing.securities.model.Domain;
import com.investing.securities.repository.AbstractRepository;

import java.util.List;
import java.util.UUID;

public abstract class AbstractService<E extends Domain, R extends AbstractRepository<E>> implements CommonService<E> {

    protected R repository;

    AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E e) {
        return repository.save(e);
    }

    @Override
    public E update(E e) {
        return repository.save(e);
    }

    @Override
    public E findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not such resource"));
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }
}
