package com.epam.testlab.service;

import com.epam.lab.dto.TagDto;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.impl.TagDaoImpl;
import com.epam.lab.service.TagService;
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
public class TagServiceImplTest {

    @Autowired
    private TagService tagService;

    @MockBean
    public TagDaoImpl tagDao;

    @Test
    public void testCreate() {
        Tag tagToCreate = new Tag("Mike");
        Mockito.when(tagDao.create(tagToCreate)).thenReturn(true);
        Mockito.when(tagDao.isNotExist(tagToCreate)).thenReturn(true);
        assertTrue(tagService.create(tagService.convertToDto(tagToCreate)));
    }

    @Test
    public void testDelete() {
        Tag tagToDelete = new Tag(1L, "Computer Games");
        Mockito.when(tagDao.isExist(1L)).thenReturn(true);
        Mockito.when(tagDao.delete(tagToDelete)).thenReturn(true);
        assertTrue(tagService.delete(tagService.convertToDto(tagToDelete)));
    }

    @Test
    public void testUpdate() {
        Tag tagToUpdate = new Tag(1L, "Computer Games");
        Mockito.when(tagDao.isExist(1L)).thenReturn(true);
        Mockito.when(tagDao.update(tagToUpdate)).thenReturn(tagToUpdate);
        Mockito.when(tagDao.isNotExist(tagToUpdate)).thenReturn(true);
        assertEquals(tagService.update(tagService.convertToDto(tagToUpdate)), new TagDto(1L, "Computer Games"));
    }

    @Test
    public void testFindById() {
        Tag tagToFind = new Tag(1L, "Computer Games");
        Mockito.when(tagDao.isExist(1L)).thenReturn(true);
        Mockito.when(tagDao.findById(1L)).thenReturn(tagToFind);
        assertEquals(new TagDto(1L, "Computer Games"),tagService.findById(1L));
    }

}
