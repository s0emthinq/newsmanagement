package com.epam.testlab.service;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.repository.NewsDao;
import com.epam.lab.repository.TagDao;
import com.epam.lab.service.NewsService;
import com.epam.testlab.configuration.ServiceTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfiguration.class)
public class NewsServiceImplTest {

    @Autowired
    private NewsService newsService;

    @MockBean
    public NewsDao newsDao;

    @MockBean
    public AuthorDao authorDao;

    @MockBean
    public TagDao tagDao;

    @Test
    public void testCreate() {
        News news = new News("Top 10 exercises at home",
                "Learn Top 10 exercises at home",
                "The qualifier will be played using a single-elimination BO1 bracket",
                LocalDate.now(),
                LocalDate.now());
        Tag tag = new Tag("Kazkhstan");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        news.setTags(tags);
        Author author = new Author("Aila", "Plastic Memories");
        news.setAuthor(author);
        Mockito.when(newsDao.isNotExist(news)).thenReturn(true);
        Mockito.when(newsDao.create(news)).thenReturn(true);
        Mockito.when(authorDao.isNotExist(author)).thenReturn(true);
        Mockito.when(tagDao.isNotExist(tag)).thenReturn(true);
        assertTrue(newsService.create(newsService.convertToDto(news)));
    }

    @Test
    public void testDelete() {
        Mockito.when(newsDao.delete(1L)).thenReturn(true);
        Mockito.when(newsDao.isExist(1L)).thenReturn(true);
        assertTrue(newsService.delete(1L));
    }

    @Test
    public void testUpdate() {
        News news = new News(2L, "EU Minor Open Qualifier",
                "The third open qualifier for the Europe Minor kicks off at 20:00",
                "The qualifier will be played using a single-elimination BO1 bracket",
                LocalDate.parse("2020-02-09"),
                LocalDate.parse("2020-02-09"));
        Mockito.when(newsDao.isExist(2L)).thenReturn(true);
        Mockito.when(newsDao.isNotExist(news)).thenReturn(true);
        Mockito.when(newsDao.update(news)).thenReturn(news);
        assertEquals(newsService.update(newsService.convertToDto(news)), new NewsDto(2L, "EU Minor Open Qualifier",
                "The third open qualifier for the Europe Minor kicks off at 20:00",
                "The qualifier will be played using a single-elimination BO1 bracket",
                LocalDate.parse("2020-02-09"),
                LocalDate.parse("2020-02-09")));
    }

    @Test
    public void testFindById() {
        News news = new News(2L, "EU Minor Open Qualifier",
                "The third open qualifier for the Europe Minor kicks off at 20:00",
                "The qualifier will be played using a single-elimination BO1 bracket",
                LocalDate.parse("2020-02-09"),
                LocalDate.parse("2020-02-09"));
        Mockito.when(newsDao.isExist(1L)).thenReturn(true);
        Mockito.when(newsDao.findById(1L)).thenReturn(new News(1L, "EU Minor Open Qualifier",
                "The third open qualifier for the Europe Minor kicks off at 20:00",
                "The qualifier will be played using a single-elimination BO1 bracket",
                LocalDate.parse("2020-02-09"),
                LocalDate.parse("2020-02-09")));
        assertEquals(new NewsDto(1L, "EU Minor Open Qualifier",
                "The third open qualifier for the Europe Minor kicks off at 20:00",
                "The qualifier will be played using a single-elimination BO1 bracket",
                LocalDate.parse("2020-02-09"),
                LocalDate.parse("2020-02-09")), newsService.findById(1L));
    }

}
