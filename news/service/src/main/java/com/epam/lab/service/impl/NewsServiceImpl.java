package com.epam.lab.service.impl;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.exception.EntityAlreadyExistsException;
import com.epam.lab.exception.NoSuchEntityException;
import com.epam.lab.model.News;
import com.epam.lab.model.SearchCriteria;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.repository.NewsDao;
import com.epam.lab.repository.TagDao;
import com.epam.lab.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsDao newsDao;
    private TagDao tagDao;
    private AuthorDao authorDao;
    private ModelMapper modelMapper;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao, AuthorDao authorDao, TagDao tagDao, ModelMapper modelMapper) {
        this.newsDao = newsDao;
        this.authorDao = authorDao;
        this.tagDao = tagDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean create(NewsDto newsDto) {
        News news = convertToEntity(newsDto);
        news.setCreationDate(LocalDate.now());
        news.setModificationDate(LocalDate.now());

        if (newsDao.isNotExist(news)) {
            return newsDao.create(news);
        } else {
            throw new EntityAlreadyExistsException("Can't create news" + news + " because this news already exists");
        }
    }

    @Override
    public NewsDto findById(Long id) {
        if (newsDao.isExist(id)) {
            return convertToDto(newsDao.findById(id));
        } else {
            throw new NoSuchEntityException("No news found with id = " + id);
        }
    }

    @Override
    public Collection<NewsDto> readAll() {
        Set<NewsDto> newsDtos = new HashSet<>();
        Collection<News> newsList = newsDao.readAll();
        newsList.forEach(news -> {
            NewsDto newsDto = convertToDto(news);
            newsDtos.add(newsDto);
        });
        return newsDtos;
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        Long id = newsDto.getId();
        News news = convertToEntity(newsDto);
        if (newsDao.isExist(id)) { // if news with given id exists
            if (newsDao.isNotExist(news)) { // if news with given title and full text doesn't already exist
                return convertToDto(newsDao.update(news));
            } else {
                throw new EntityAlreadyExistsException("Can't update news with id "
                        + id + " because news with title = "
                        + news.getTitle() + " and full text = "
                        + news.getFullText() + " already exists");
            }
        } else {
            throw new NoSuchEntityException("No news found with id = " + id + " to update");
        }
    }

    @Override
    public boolean delete(NewsDto newsDto) {
        Long id = newsDto.getId();
        if (newsDao.isExist(id)) {
            return newsDao.delete(convertToEntity(newsDto));
        } else {
            throw new NoSuchEntityException("No news found with id = " + id + " to delete");
        }
    }

    @Override
    public boolean delete(Long id) {
        if (newsDao.isExist(id)) {
            return newsDao.delete(id);
        } else {
            throw new NoSuchEntityException("No news found with id = " + id + " to delete");
        }
    }

    @Override
    public List<NewsDto> sort(SearchCriteria searchCriteria) {
        Set<News> newsList = newsDao.sort(searchCriteria);
        List<NewsDto> newsDtos = new ArrayList<>();
        newsList.forEach(news -> {
            NewsDto newsDto = convertToDto(news);
            newsDtos.add(newsDto);
        });
        return newsDtos;
    }

    @Override
    public NewsDto convertToDto(News news) {
        return modelMapper.map(news, NewsDto.class);
    }

    @Override
    public News convertToEntity(NewsDto newsDto) {
        return modelMapper.map(newsDto, News.class);
    }
}
