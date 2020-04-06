package com.epam.labtest.repository;

import com.epam.lab.model.News;
import com.epam.lab.repository.NewsDao;
import com.epam.labtest.configuration.TestDatabaseConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDatabaseConfigurator.class)
@Sql(value = "classpath:fill_in_test_database.sql")
@Sql(value = "classpath:clear_test_database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class NewsDaoImplTest {

    private static final Long ID_OF_EXISTING_NEWS = 10L;
    private static final News EXISTING_NEWS = new News(ID_OF_EXISTING_NEWS,
            "Epams 10000 employees",
            "Epams reached 10000 employees in Belarus",
            "Epams' 10000 empoyee is their former worker",
            LocalDate.parse("2019-01-16"),
            LocalDate.parse("2019-01-16"));
    private static final Long ID_OF_NOT_EXISTING_NEWS = 228L;
    private static final News NOT_EXISTING_NEWS = new News(ID_OF_NOT_EXISTING_NEWS,
            "Fake title",
            "Fake short text",
            "Fake full text",
            LocalDate.parse("2099-01-16"),
            LocalDate.parse("2099-01-16"));

    @Autowired
    private NewsDao newsDao;

    @Test
    public void testCreate() {
        News newsToCreate = new News("NewNews",
                "Some short text",
                "Some full text",
                LocalDate.parse("2020-02-09"),
                LocalDate.parse("2020-02-09"));
        assertTrue(newsDao.create(newsToCreate));
    }

    @Test
    public void testFindById() {
        News foundNews = newsDao.findById(ID_OF_EXISTING_NEWS);
        assertEquals(EXISTING_NEWS, foundNews);
    }

    @Test
    public void testReadAll() {
        Set<News> news = new HashSet<>();
        News news1 = new News(1L,
                "Na'vi transfer",
                "Na'Vi bought Perfecto",
                "Na'vi bought Perfecto and replaced Guardian",
                LocalDate.parse("2020-01-10"),
                LocalDate.parse("2020-01-10")
        );
        News news2 = new News(2L,
                "EU Minor Open Qualifier",
                "The third open qualifier for the Europe Minor kicks off at 20:00",
                "The qualifier will be played using a single-elimination BO1 bracket",
                LocalDate.parse("2020-02-09"),
                LocalDate.parse("2020-02-09")
        );
        News news4 = new News(4L,
                "Smoking unexpected fact",
                "Smoking turned out to be more harmful",
                "After the research of Britain scinetist it tourned out that smoking is more harmful than it was thought to be",
                LocalDate.parse("2010-02-09"),
                LocalDate.parse("2010-02-09")
        );
        News news5 = new News(5L,
                "SpaceX shuttle",
                "SpaceX shuttle was launched by Ilon Mask",
                "After a long time and big amout of work SpaceX shuttle was launched by Ilon Mask ",
                LocalDate.parse("2019-02-09"),
                LocalDate.parse("2019-02-09")
        );
        News news6 = new News(6L,
                "Chester Bennigton suicide",
                "Chester Bennigton commited a suicide",
                "Unfortunately, after a long and hard depression Chester Bennigton didn't manage to overcome his demons and ended up his life throught a suicide.",
                LocalDate.parse("2017-07-20"),
                LocalDate.parse("2017-07-20")
        );
        News news7 = new News(7L,
                "Linkin Park Numb",
                "Linkin Park's Numb video reached 1 billion views",
                "Linkin Park's Numb video reached 1 billion views on YouTube 15 years after it's release.",
                LocalDate.parse("2018-11-14"),
                LocalDate.parse("2018-11-14")
        );
        News news8 = new News(8L,
                "Eminem Not Afraid",
                "Eminem released new single Not Afraid",
                "After a hard recovery from drugs addiction Eminem released new single Not Afraid",
                LocalDate.parse("2010-04-29"),
                LocalDate.parse("2010-04-29")
        );
        News news9 = new News(9L,
                "Russians to Olympiad",
                "Russian volleyball team qualified for Olympiad",
                "Russian volleyball team qualified for Olympiad in London 2012",
                LocalDate.parse("2012-04-29"),
                LocalDate.parse("2012-04-29")
        );
        News news10 = new News(10L,
                "Epams 10000 employees",
                "Epams reached 10000 employees in Belarus",
                "Epams' 10000 empoyee is their former worker",
                LocalDate.parse("2019-01-16"),
                LocalDate.parse("2019-01-16")
        );
        News news11 = new News(11L,
                "Singing surname", "I have singing surname",
                "I am very likely to have a singing surname",
                LocalDate.parse("2020-02-12"),
                LocalDate.parse("2020-02-12")
        );
        News news12 = new News(12L,
                "Tyson lose",
                "Tyson loses to Holyfield",
                "Tyson loses to Holyfield after biting his ear",
                LocalDate.parse("2001-03-12"),
                LocalDate.parse("2001-03-12")
        );
        News news13 = new News(13L,
                "Flusha cheater",
                "Flush caught cheating",
                "Flush caught cheating at ESL One New York",
                LocalDate.parse("2015-03-03"),
                LocalDate.parse("2015-03-03")
        );
        News news14 = new News(14L,
                "Noble thief",
                "Robin Hood steals money",
                "Robin Hood steals money from the rich and gives them away to the poor",
                LocalDate.parse("1746-08-15"),
                LocalDate.parse("1746-08-15")
        );
        News news15 = new News(15L,
                "Barabek",
                "Just a saying",
                "Just a saying not to pronounce it",
                LocalDate.parse("1543-06-30"),
                LocalDate.parse("1543-06-30")
        );

        Collections.addAll(news, news1, news2, news4, news5, news6, news7, news8, news9, news10, news11, news12, news13,
                news14, news15);

        assertEquals(news, newsDao.readAll());
    }

    @Test
    public void testUpdate() {
        News newsToUpdate = new News(ID_OF_EXISTING_NEWS, "Qualifier",
                "Updated short text",
                "Updated full text",
                LocalDate.parse("2020-02-09"),
                LocalDate.parse("2020-02-09"));
        assertEquals(newsToUpdate, newsDao.update(newsToUpdate));
    }

    @Test
    public void testDelete() {
        assertTrue(newsDao.delete(EXISTING_NEWS));
    }

    @Test
    public void testIsExistPositive() {
        assertTrue(newsDao.isExist(EXISTING_NEWS));
    }

    @Test
    public void testIsExistNegative() {
        assertFalse(newsDao.isExist(NOT_EXISTING_NEWS));
    }

    @Test
    public void testIsNotExistPositive() {
        assertTrue(newsDao.isNotExist(NOT_EXISTING_NEWS));
    }

    @Test
    public void testIsNotExistNegative() {
        assertFalse(newsDao.isNotExist(EXISTING_NEWS));
    }
}
