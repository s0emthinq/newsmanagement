package com.epam.lab.service;

import com.epam.lab.dto.BaseDto;
import com.epam.lab.model.BaseEntity;

import java.util.Collection;

public interface BaseService<K, T extends BaseDto, E extends BaseEntity> {
    boolean create(T t);

    Collection<T> readAll();

    T findById(K id);

    T update(T t);

    boolean delete(T t);

    boolean delete(K k);

    T convertToDto(E e);

    E convertToEntity(T t);
}
