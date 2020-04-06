--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.0

-- Started on 2020-02-12 11:30:51

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2867 (class 0 OID 16596)
-- Dependencies: 202
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.author (id, name, surname) VALUES (1, 'Robin', 'Kool');
INSERT INTO public.author (id, name, surname) VALUES (2, 'Denis', 'Semenihin');
INSERT INTO public.author (id, name, surname) VALUES (3, 'Ilon', ' Mask');
INSERT INTO public.author (id, name, surname) VALUES (4, 'Mike', 'Shinoda');
INSERT INTO public.author (id, name, surname) VALUES (5, 'Marshall', 'Mathers');
INSERT INTO public.author (id, name, surname) VALUES (6, 'Victor', 'Poletaev');
INSERT INTO public.author (id, name, surname) VALUES (7, 'Arcadiy', 'Dobkin');
INSERT INTO public.author (id, name, surname) VALUES (8, 'Mike', 'Tyson');
INSERT INTO public.author (id, name, surname) VALUES (9, 'Mike', 'Singer');
INSERT INTO public.author (id, name, surname) VALUES (10, 'Robin', 'Ronquist');
INSERT INTO public.author (id, name, surname) VALUES (11, 'Robin', 'Hood');
INSERT INTO public.author (id, name, surname) VALUES (12, 'Robin', 'Bobin');


--
-- TOC entry 2869 (class 0 OID 16601)
-- Dependencies: 204
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (2, 'EU Minor Open Qualifier', 'The third open qualifier for the Europe Minor kicks off at 20:00', 'The qualifier will be played using a single-elimination BO1 bracket', '2020-02-09', '2020-02-09');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (4, 'Smoking unexpected fact', 'Smoking turned out to be more harmful', 'After the research of Britain scinetist it tourned out that smoking is more harmful than it was thought to be', '2010-02-09', '2010-02-09');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (5, 'SpaceX shuttle', 'SpaceX shuttle was launched by Ilon Mask', 'After a long time and big amout of work SpaceX shuttle was launched by Ilon Mask ', '2019-02-09', '2019-02-09');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (6, 'Chester Bennigton suicide', 'Chester Bennigton commited a suicide', 'Unfortunately, after a long and hard depression Chester Bennigton didn''t manage to overcome his demons and ended up his life throught a suicide.', '2017-07-20', '2017-07-20');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (7, 'Linkin Park Numb', 'Linkin Park''s Numb video reached 1 billion views', 'Linkin Park''s Numb video reached 1 billion views on YouTube 15 years after it''s release.', '2018-11-14', '2018-11-14');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (1, 'Na''vi transfer', 'Na''Vi bought Perfecto', 'Na''vi bought Perfecto and replaced Guardian', '2020-01-10', '2020-01-10');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (8, 'Eminem Not Afraid', 'Eminem released new single Not Afraid', 'After a hard recovery from drugs addiction Eminem released new single Not Afraid', '2010-04-29', '2010-04-29');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (9, 'Russians to Olympiad', 'Russian volleyball team qualified for Olympiad', 'Russian volleyball team qualified for Olympiad in London 2012', '2012-04-29', '2012-04-29');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (10, 'Epams 10000 employees', 'Epams reached 10000 employees in Belarus', 'Epams'' 10000 empoyee is their former worker', '2019-01-16', '2019-01-16');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (11, 'Singing surname', 'I have singing surname', 'I am very likely to have a singing surname
', '2020-02-12', '2020-02-12');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (12, 'Tyson lose', 'Tyson loses to Holyfield', 'Tyson loses to Holyfield after biting his ear', '2001-03-12', '2001-03-12');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (13, 'Flusha cheater', 'Flush caught cheating', 'Flush caught cheating at ESL One New York', '2015-03-03', '2015-03-03');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (14, 'Noble thief', 'Robin Hood steals money', 'Robin Hood steals money from the rich and gives them away to the poor', '1746-08-15', '1746-08-15');
INSERT INTO public.news (id, title, short_text, full_text, creation_date, modification_date) VALUES (15, 'Barabek', 'Just a saying', 'Just a saying not to pronounce it', '1543-06-30', '1543-06-30');


--
-- TOC entry 2870 (class 0 OID 16607)
-- Dependencies: 205
-- Data for Name: news_author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.news_author (news_id, author_id) VALUES (1, 1);
INSERT INTO public.news_author (news_id, author_id) VALUES (2, 1);
INSERT INTO public.news_author (news_id, author_id) VALUES (4, 2);
INSERT INTO public.news_author (news_id, author_id) VALUES (5, 3);
INSERT INTO public.news_author (news_id, author_id) VALUES (6, 4);
INSERT INTO public.news_author (news_id, author_id) VALUES (7, 4);
INSERT INTO public.news_author (news_id, author_id) VALUES (8, 5);
INSERT INTO public.news_author (news_id, author_id) VALUES (9, 6);
INSERT INTO public.news_author (news_id, author_id) VALUES (10, 7);
INSERT INTO public.news_author (news_id, author_id) VALUES (12, 8);
INSERT INTO public.news_author (news_id, author_id) VALUES (11, 9);
INSERT INTO public.news_author (news_id, author_id) VALUES (13, 10);
INSERT INTO public.news_author (news_id, author_id) VALUES (14, 11);
INSERT INTO public.news_author (news_id, author_id) VALUES (15, 12);


--
-- TOC entry 2874 (class 0 OID 16618)
-- Dependencies: 209
-- Data for Name: tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tag (id, name) VALUES (1, 'Computer games');
INSERT INTO public.tag (id, name) VALUES (2, 'CS:GO');
INSERT INTO public.tag (id, name) VALUES (3, 'Minor');
INSERT INTO public.tag (id, name) VALUES (4, 'Transfer');
INSERT INTO public.tag (id, name) VALUES (5, 'Health
');
INSERT INTO public.tag (id, name) VALUES (6, 'Space');
INSERT INTO public.tag (id, name) VALUES (7, 'Science');
INSERT INTO public.tag (id, name) VALUES (8, 'Music');
INSERT INTO public.tag (id, name) VALUES (9, 'Linkin Park');
INSERT INTO public.tag (id, name) VALUES (10, 'Chester Bennington');
INSERT INTO public.tag (id, name) VALUES (11, 'Smoking');
INSERT INTO public.tag (id, name) VALUES (12, 'Eminem');
INSERT INTO public.tag (id, name) VALUES (13, 'Sport');
INSERT INTO public.tag (id, name) VALUES (14, 'IT');
INSERT INTO public.tag (id, name) VALUES (15, 'Epam');
INSERT INTO public.tag (id, name) VALUES (16, 'Box');
INSERT INTO public.tag (id, name) VALUES (17, 'Major');
INSERT INTO public.tag (id, name) VALUES (18, 'Criminal');
INSERT INTO public.tag (id, name) VALUES (19, 'Robin Hood');
INSERT INTO public.tag (id, name) VALUES (20, 'Nonsense');
INSERT INTO public.tag (id, name) VALUES (21, 'Saying');


--
-- TOC entry 2872 (class 0 OID 16612)
-- Dependencies: 207
-- Data for Name: news_tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.news_tag (news_id, tag_id) VALUES (1, 1);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (1, 2);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (1, 4);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (2, 1);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (2, 2);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (2, 3);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (4, 11);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (4, 5);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (5, 7);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (5, 6);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (6, 8);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (6, 9);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (6, 10);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (7, 8);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (7, 9);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (8, 8);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (8, 12);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (9, 13);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (10, 14);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (10, 15);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (11, 8);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (12, 13);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (12, 16);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (13, 1);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (13, 2);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (13, 17);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (14, 18);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (14, 19);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (15, 20);
INSERT INTO public.news_tag (news_id, tag_id) VALUES (15, 21);


--
-- TOC entry 2876 (class 0 OID 16623)
-- Dependencies: 211
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, name, surname, login, password) VALUES (1, 'Vladislav', 'Avsievich', 's0emthinq', 'admin
');
INSERT INTO public.users (id, name, surname, login, password) VALUES (2, 'Vadim', 'Krivitskiy', 's0ny', 'user');


--
-- TOC entry 2873 (class 0 OID 16615)
-- Dependencies: 208
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.roles (user_id, role_name) VALUES (1, 'admin');
INSERT INTO public.roles (user_id, role_name) VALUES (2, 'user');


--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 203
-- Name: author_id_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.author_id_sequence', 12, true);


--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 206
-- Name: news_id_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_id_sequence', 15, true);


--
-- TOC entry 2885 (class 0 OID 0)
-- Dependencies: 210
-- Name: tag_id_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tag_id_sequence', 21, true);


--
-- TOC entry 2886 (class 0 OID 0)
-- Dependencies: 212
-- Name: users_id_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_sequence', 2, true);


-- Completed on 2020-02-12 11:30:51

--
-- PostgreSQL database dump complete
--

