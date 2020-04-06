package com.epam.lab.repository;

import com.epam.lab.model.BaseEntity;

import java.util.Collection;

public interface BaseDao<K, T extends BaseEntity> {
    boolean create(T t);

    T findById(K id);

    Collection<T> readAll();

    T update(T t);

    boolean delete(T t);

    boolean delete(K k);

    boolean isExist(Long id);

    /** This method checks whether the entity with same parameters as t exists in database.
     * It is used, when checking if tag exists while adding news, for example. **/

    boolean isExist(T t);

    /** This method is created for fast and easy reading,
     *  for example, not to lose "!" in expressions like "!isExists(t). "
     **/
    boolean isNotExist(T t);

    boolean isNotExist(Long id);
}

