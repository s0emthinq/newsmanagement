package com.epam.lab.service;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.model.News;
import com.epam.lab.model.SearchCriteria;

import java.util.List;

public interface NewsService extends BaseService<Long, NewsDto, News> {
    List<NewsDto> sort(SearchCriteria searchCriteria);
}
