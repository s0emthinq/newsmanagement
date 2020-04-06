package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    public static final String TABLE_NAME_AUTHOR = "author";
    public static final String CLM_NAME_ID = "id";
    public static final String CLM_NAME_NAME = "name";
    public static final String CLM_NAME_SURNAME = "surname";

    private static final String JPQL_READ_ALL_AUTHORS = "select a from Author a";
    private static final String JPQL_DELETE_AUTHOR_BY_ID = "delete from Author where id = :id";
    private static final String JPQL_FIND_AUTHOR_BY_ID = "select a from Author a where id = :id";
    private static final String JPQL_FIND_AUTHOR_BY_NAME_AND_SURNAME
            = "select a from Author a where name = :name and surname = :surname";

    private static final String ERROR_500_MESSAGE = "Oops, something went wrong on the server :( Please, try later.";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean create(Author author) {
        try {
            entityManager.persist(author);
            return true;
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    public Author findById(Long id) {
            return entityManager.find(Author.class, id);
    }

    @Override
    public Set<Author> readAll() {
        try {
            return new HashSet<Author>(entityManager.createQuery(JPQL_READ_ALL_AUTHORS).getResultList());
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    @Transactional
    public Author update(Author author) {
        try {
            return entityManager.merge(author);
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    @Transactional
    public boolean delete(Author author) {
        try {
            entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
            return true;
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        try {
            return entityManager.createQuery(JPQL_DELETE_AUTHOR_BY_ID)
                    .setParameter(CLM_NAME_ID, id).executeUpdate() == 1;
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    public boolean isExist(Long id) {
        try {
            entityManager.createQuery(JPQL_FIND_AUTHOR_BY_ID).setParameter(CLM_NAME_ID, id).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean isExist(Author author) {
        try {
            entityManager.createQuery(JPQL_FIND_AUTHOR_BY_NAME_AND_SURNAME)
                    .setParameter(CLM_NAME_NAME, author.getName())
                    .setParameter(CLM_NAME_SURNAME, author.getSurname())
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean isNotExist(Author author) {
        return !isExist(author);
    }

    @Override
    public boolean isNotExist(Long id) {
        return !isExist(id);
    }
}
