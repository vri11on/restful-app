package com.example.restfulapp.dao;

import com.example.restfulapp.entity.BaseEntity;

import java.util.Collection;

public interface CrudDao<T extends BaseEntity> {
    T create(T t);
    T update(T t);
    T get(Long id);
    Boolean delete(T t);
    Boolean delete(Long id);
    Collection<T> getAll(Integer limit);
    T patchUpdate(T t);
}
