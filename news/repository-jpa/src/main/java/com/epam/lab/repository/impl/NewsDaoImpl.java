package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.SearchCriteria;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.NewsDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

@Repository
public class NewsDaoImpl implements NewsDao {

    public static final String TABLE_NAME_NEWS = "news";
    public static final String CLM_NAME_ID = "id";
    public static final String CLM_NAME_TITLE = "title";
    public static final String CLM_NAME_SHORT_TEXT = "short_text";
    public static final String CLM_NAME_FULL_TEXT = "full_text";
    public static final String CLM_NAME_CREATION_DATE = "creation_date";
    public static final String CLM_NAME_MODIFICATION_DATE = "modification_date";

    private static final String JPQL_READ_ALL_NEWS = "select n from News n";
    private static final String JPQL_FIND_NEWS_BY_ID = "select n from News n where id = :id";
    private static final String JPQL_DELETE_NEWS_BY_ID = "delete from News where id = :id";
    public static final String JPQL_FIND_NEWS_BY_TITLE_AND_FULL_TEXT
            = "select n from News n where title = :title and full_text = :full_text";

    private static final String ERROR_500_MESSAGE = "Oops, something went wrong on the server :( Please, try later.";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean create(News news) {
        try {
            entityManager.persist(news);
            return true;
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    public News findById(Long id) {
        return entityManager.find(News.class, id);
    }

    @Override
    public Set<News> readAll() {
        try {
            return new HashSet<News>(entityManager.createQuery(JPQL_READ_ALL_NEWS).getResultList());
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    @Transactional
    public News update(News news) {
        try {
            return entityManager.merge(news);
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    @Transactional
    public boolean delete(News news) {
        try {
            entityManager.remove(entityManager.contains(news) ? news : entityManager.merge(news));
            return true;
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        try {
            return entityManager.createQuery(JPQL_DELETE_NEWS_BY_ID).setParameter(CLM_NAME_ID, id).executeUpdate() == 1;
        } catch (PersistenceException e) {
            throw new DaoException(ERROR_500_MESSAGE, e);
        }
    }

    @Override
    public Set<News> sort(SearchCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> newsRoot = criteriaQuery.from(News.class);
        if (searchCriteria.getAuthor() != null) {
            Join<News, Author> author = newsRoot.join("author");
            criteriaQuery.where(criteriaBuilder.equal(author.get("name"), searchCriteria.getAuthor().getName()));
        }
        if (searchCriteria.getTags() != null) {
            Join<News, Tag> tags = newsRoot.join("tag");
            searchCriteria.getTags().forEach(tagName ->
                    criteriaQuery.where(criteriaBuilder.equal(tags.get("name"), tagName))
            );
        }
        criteriaQuery.select(newsRoot);
        TypedQuery<News> query = entityManager.createQuery(criteriaQuery);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public boolean isExist(Long id) {
        try {
            entityManager.createQuery(JPQL_FIND_NEWS_BY_ID).setParameter(CLM_NAME_ID, id).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean isExist(News news) {
        try {
            entityManager.createQuery(JPQL_FIND_NEWS_BY_TITLE_AND_FULL_TEXT)
                    .setParameter(CLM_NAME_TITLE, news.getTitle())
                    .setParameter(CLM_NAME_FULL_TEXT, news.getFullText())
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean isNotExist(News news) {
        return !isExist(news);
    }

    @Override
    public boolean isNotExist(Long id) {
        return !isExist(id);
    }
}
