package com.epam.labtest.repository;

import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorDao;
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
public class AuthorDaoImplTest {

    private static final Long ID_OF_EXISTING_AUTHOR = 1L;
    private static final Author EXISTING_AUTHOR = new Author(ID_OF_EXISTING_AUTHOR, "Robin", "Kool");
    private static final Long ID_OF_NOT_EXISTING_AUTHOR = 322L;
    private static final Author NOT_EXISTING_AUTHOR = new Author(ID_OF_NOT_EXISTING_AUTHOR,
            "Fake",
            "Author");

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testCreate() {
        Author authorToCreate = new Author("Karrigan", "Mouz");
        assertTrue(authorDao.create(authorToCreate));
    }

    @Test
    public void testFindById() {
        Author foundAuthor = authorDao.findById(ID_OF_EXISTING_AUTHOR);
        assertEquals(EXISTING_AUTHOR, foundAuthor);
    }

    @Test
    public void testReadAll() {
        Author author1 = new Author(1L, "Robin", "Kool");
        Author author2 = new Author(2L, "Denis", "Semenihin");
        Author author3 = new Author(3L, "Ilon", "Mask");
        Author author4 = new Author(4L, "Mike", "Shinoda");
        Author author5 = new Author(5L, "Marshall", "Mathers");
        Author author6 = new Author(6L, "Victor", "Poletaev");
        Author author7 = new Author(7L, "Arcadiy", "Dobkin");
        Author author8 = new Author(8L, "Mike", "Tyson");
        Author author9 = new Author(9L, "Mike", "Singer");
        Author author10 = new Author(10L, "Robin", "Ronquist");
        Author author11 = new Author(11L, "Robin", "Hood");
        Author author12 = new Author(12L, "Robin", "Bobin");
        Set<Author> authors = new HashSet<>();
        Collections.addAll(authors, author1, author2, author3, author4, author5, author6,
                author7, author8, author9, author10, author11, author12);
        assertEquals(authors, authorDao.readAll());
    }

    @Test
    public void testUpdate() {
        Author authorToUpdate = new Author(ID_OF_EXISTING_AUTHOR, "ropz", "mousesports");
        Author updatedAuthor = authorDao.update(authorToUpdate);
        assertEquals(authorToUpdate, updatedAuthor);
    }

    @Test
    public void testDelete() {
        assertTrue(authorDao.delete(EXISTING_AUTHOR));
    }

    @Test
    public void testIsExistPositive() {
        assertTrue(authorDao.isExist(EXISTING_AUTHOR));
    }

    @Test
    public void testIsExistNegative() {
        assertFalse(authorDao.isExist(NOT_EXISTING_AUTHOR));
    }

    @Test
    public void testIsNotExistPositive() {
        assertTrue(authorDao.isNotExist(NOT_EXISTING_AUTHOR));
    }

    @Test
    public void testIsNotExistNegative() {
        assertFalse(authorDao.isNotExist(EXISTING_AUTHOR));
    }
}
