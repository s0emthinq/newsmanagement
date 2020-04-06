package com.epam.testlab.service;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.model.Author;
import com.epam.lab.repository.impl.AuthorDaoImpl;
import com.epam.lab.service.AuthorService;
import com.epam.testlab.configuration.ServiceTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfiguration.class)
public class AuthorServiceImplTest {
    @Autowired
    private AuthorService authorService;

    @MockBean
    public AuthorDaoImpl authorDao;

    @Test
    public void testCreate() {
        Author author = new Author("Freddy", "Johansson");
        Mockito.when(authorDao.isNotExist(author)).thenReturn(true);
        Mockito.when(authorDao.create(author)).thenReturn(true);
        assertTrue(authorService.create(authorService.convertToDto(author)));
    }

    @Test
    public void testDelete() {
        Author author = new Author(1L,"Robin","Kool");
        Mockito.when(authorDao.isExist(1L)).thenReturn(true);
        Mockito.when(authorDao.delete(author)).thenReturn(true);
        AuthorDto authorDto = new AuthorDto(1L,"Robin","Kool");
        assertTrue(authorService.delete(authorDto));
    }

    @Test
    public void testUpdate() {
        Author author = new Author(1L,"Look", "Kool");
        Mockito.when(authorDao.update(author)).thenReturn(author);
        Mockito.when(authorDao.isExist(1L)).thenReturn(true);
        Mockito.when(authorDao.isNotExist(author)).thenReturn(true);
        assertEquals(authorService.update(authorService.convertToDto(author)),
                new AuthorDto("Look", "Kool"));
    }

    @Test
    public void testFindById() {
        Author author = new Author(1L,"Robin", "Kool");
        Mockito.when(authorDao.isExist(1L)).thenReturn(true);
        Mockito.when(authorDao.findById(1L)).thenReturn(author);
        assertEquals(new AuthorDto("Robin", "Kool"), authorService.findById(1L));
    }
}
