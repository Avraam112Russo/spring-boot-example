package com.example.springsterterdemo.repository;

import java.util.List;
import java.util.Optional;

public interface MyRepository<T> {
    T save(T entity);
    void postConstruct();
    void preDestroy();
    Optional<T> getEntityById(Integer id);
    List<T> getAll();
}
