package com.epam.lab.repository;

import com.epam.lab.model.News;
import com.epam.lab.model.SearchCriteria;

import java.util.Set;

public interface NewsDao extends BaseDao<Long, News> {
    Set<News> sort(SearchCriteria searchCriteria);
}
