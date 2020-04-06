package com.epam.lab.controller;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.model.SearchCriteria;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public Collection<NewsDto> readAll() {
        return newsService.readAll();
    }

    @GetMapping(value = "/{id}")
    public NewsDto findOne(@PathVariable Long id) {
        return newsService.findById(id);
    }

    @PostMapping
    public boolean create(@RequestBody NewsDto newsDto) {
        return newsService.create(newsDto);
    }

    @PutMapping(value = "/{id}")
    public NewsDto update(@PathVariable Long id, @RequestBody NewsDto newsDto) {
        newsDto.setId(id);
        return newsService.update(newsDto);
    }

    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable Long id) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(id);
        return newsService.delete(newsDto);
    }

    @GetMapping(value = "/sort")
    public List<NewsDto> sort(@ModelAttribute SearchCriteria searchCriteria) {
        return newsService.sort(searchCriteria);
    }
}
