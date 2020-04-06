package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TagDaoImpl implements TagDao {

    public static final String TABLE_NAME_TAG = "tag";
    public static final String CLM_NAME_ID = "id";
    public static final String CLM_NAME_NAME = "name";

    private static final String JPQL_READ_ALL_TAGS = "select t from Tag t";
    private static final String JPQL_DELETE_TAG_BY_ID = "delete from Tag where id = :id";
    private static final String JPQL_FIND_TAG_BY_ID = "select t from Tag t where id = :id";
    private static final String JPQL_FIND_TAG_BY_NAME = "select t from Tag t where name = :name";

    private static final String ERROR_500_MESSAGE = "Oops, something went wrong on the server :( Please, try later.";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean create(Tag tag) {
        try {
            entityManager.persist(tag);
            return true;
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    public Tag findById(Long id) {
        return entityManager.find(Tag.class, id);
    }

    @Override
    public Set<Tag> readAll() {
        try {
            return new HashSet<Tag>(entityManager.createQuery(JPQL_READ_ALL_TAGS).getResultList());
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    @Transactional
    public Tag update(Tag tag) {
        try {
            return entityManager.merge(tag);
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    @Transactional
    public boolean delete(Tag tag) {
        try {
            entityManager.remove(entityManager.contains(tag) ? tag : entityManager.merge(tag));
            return true;
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        try {
            return entityManager.createQuery(JPQL_DELETE_TAG_BY_ID).setParameter(CLM_NAME_ID, id).executeUpdate() == 1;
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    public boolean isExist(Long id) {
        try {
            entityManager.createQuery(JPQL_FIND_TAG_BY_ID).setParameter(CLM_NAME_ID, id).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean isExist(Tag tag) {
        try {
            entityManager.createQuery(JPQL_FIND_TAG_BY_NAME).setParameter(CLM_NAME_NAME, tag.getName()).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean isNotExist(Tag tag) {
        return !isExist(tag);
    }

    @Override
    public boolean isNotExist(Long id) {
        return !isExist(id);
    }
}
