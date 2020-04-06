package com.epam.labtest.repository;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagDao;
import com.epam.labtest.configuration.TestDatabaseConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDatabaseConfigurator.class)
@Sql(value = "classpath:fill_in_test_database.sql")
@Sql(value = "classpath:clear_test_database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TagDaoImplTest {

    private static final Long ID_OF_EXISTING_TAG = 10L;
    private static final Long ID_OF_NOT_EXISTING_TAG = 322L;
    private static final Tag EXISTING_TAG = new Tag(ID_OF_EXISTING_TAG, "Chester Bennington");
    private static final Tag NOT_EXISTING_TAG = new Tag(ID_OF_NOT_EXISTING_TAG, "Trash");

    @Autowired
    private TagDao tagDao;


    @Test
    public void testCreate() {
        Tag tagToCreate = new Tag("Mike Shinoda");
        assertTrue(tagDao.create(tagToCreate));
    }

    @Test
    public void testFindById() {
        Tag foundTag = tagDao.findById(ID_OF_EXISTING_TAG);
        assertEquals(EXISTING_TAG, foundTag);
    }

    @Test
    public void testReadAll() {
        Tag tag1 = new Tag(1L, "Computer games");
        Tag tag2 = new Tag(2L, "CS:GO");
        Tag tag3 = new Tag(3L, "Minor");
        Tag tag4 = new Tag(4L, "Transfer");
        Tag tag5 = new Tag(5L, "Health");
        Tag tag6 = new Tag(6L, "Space");
        Tag tag7 = new Tag(7L, "Science");
        Tag tag8 = new Tag(8L, "Music");
        Tag tag9 = new Tag(9L, "Linkin Park");
        Tag tag10 = new Tag(10L, "Chester Bennington");
        Tag tag11 = new Tag(11L, "Smoking");
        Tag tag12 = new Tag(12L, "Eminem");
        Tag tag13 = new Tag(13L, "Sport");
        Tag tag14 = new Tag(14L, "IT");
        Tag tag15 = new Tag(15L, "Epam");
        Tag tag16 = new Tag(16L, "Box");
        Tag tag17 = new Tag(17L, "Major");
        Tag tag18 = new Tag(18L, "Criminal");
        Tag tag19 = new Tag(19L, "Robin Hood");
        Tag tag20 = new Tag(20L, "Nonsense");
        Tag tag21 = new Tag(21L, "Saying");
        Set<Tag> expectedTags = new HashSet<>();
        Collections.addAll(expectedTags, tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9, tag10, tag11,
                tag12, tag13, tag14, tag15, tag16, tag17, tag18, tag19, tag20, tag21);
        Set<Tag> foundTags = (Set<Tag>) tagDao.readAll();
        assertEquals(expectedTags, foundTags);
    }

    @Test
    public void testUpdate() {
        Tag tagToUpdate = new Tag(1L, "Active games");
        assertEquals(tagToUpdate, tagDao.update(tagToUpdate));
    }

    @Test
    public void testIsExistPositive() {
        assertTrue(tagDao.isExist(EXISTING_TAG));
    }

    @Test
    public void testDelete() {
        assertTrue(tagDao.delete(EXISTING_TAG));
    }

    @Test
    public void testIsExistNegative() {
        assertFalse(tagDao.isExist(NOT_EXISTING_TAG));
    }

    @Test
    public void testIsNotExistPositive() {
        assertTrue(tagDao.isNotExist(NOT_EXISTING_TAG));
    }

    @Test
    public void testIsNotExistNegative() {
        assertFalse(tagDao.isNotExist(EXISTING_TAG));
    }

}
