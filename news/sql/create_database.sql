--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-02-09 17:31:58

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16665)
-- Name: author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.author (
    id bigint NOT NULL,
    name character varying(30) NOT NULL,
    surname character varying(30) NOT NULL
);


ALTER TABLE public.author OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16668)
-- Name: author_id_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.author_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.author_id_sequence OWNER TO postgres;

--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 203
-- Name: author_id_sequence; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.author_id_sequence OWNED BY public.author.id;


--
-- TOC entry 204 (class 1259 OID 16670)
-- Name: news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news (
    id bigint NOT NULL,
    title character varying(30) NOT NULL,
    short_text character varying(100) NOT NULL,
    full_text character varying(2000) NOT NULL,
    creation_date date NOT NULL,
    modification_date date NOT NULL
);


ALTER TABLE public.news OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16676)
-- Name: news_author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news_author (
    news_id bigint NOT NULL,
    author_id bigint NOT NULL
);


ALTER TABLE public.news_author OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16679)
-- Name: news_id_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_id_sequence OWNER TO postgres;

--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 206
-- Name: news_id_sequence; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_id_sequence OWNED BY public.news.id;


--
-- TOC entry 207 (class 1259 OID 16681)
-- Name: news_tag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news_tag (
    news_id bigint NOT NULL,
    tag_id bigint NOT NULL
);


ALTER TABLE public.news_tag OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16840)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    user_id bigint NOT NULL,
    role_name character varying(30) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16687)
-- Name: tag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tag (
    id bigint NOT NULL,
    name character varying(30) NOT NULL
);


ALTER TABLE public.tag OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16690)
-- Name: tag_id_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tag_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tag_id_sequence OWNER TO postgres;

--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 209
-- Name: tag_id_sequence; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tag_id_sequence OWNED BY public.tag.id;


--
-- TOC entry 210 (class 1259 OID 16692)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    name character varying(20) NOT NULL,
    surname character varying(20) NOT NULL,
    login character varying(30) NOT NULL,
    password character varying(30) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16695)
-- Name: users_id_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_sequence OWNER TO postgres;

--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 211
-- Name: users_id_sequence; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_sequence OWNED BY public.users.id;


--
-- TOC entry 2718 (class 2604 OID 16697)
-- Name: author id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author ALTER COLUMN id SET DEFAULT nextval('public.author_id_sequence'::regclass);


--
-- TOC entry 2719 (class 2604 OID 16698)
-- Name: news id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news ALTER COLUMN id SET DEFAULT nextval('public.news_id_sequence'::regclass);


--
-- TOC entry 2720 (class 2604 OID 16699)
-- Name: tag id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag ALTER COLUMN id SET DEFAULT nextval('public.tag_id_sequence'::regclass);


--
-- TOC entry 2721 (class 2604 OID 16700)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_sequence'::regclass);


--
-- TOC entry 2723 (class 2606 OID 16702)
-- Name: author author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);


--
-- TOC entry 2727 (class 2606 OID 16704)
-- Name: news_author news_author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_author
    ADD CONSTRAINT news_author_pkey PRIMARY KEY (news_id, author_id);


--
-- TOC entry 2725 (class 2606 OID 16706)
-- Name: news news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- TOC entry 2729 (class 2606 OID 16708)
-- Name: news_tag news_tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_tag
    ADD CONSTRAINT news_tag_pkey PRIMARY KEY (news_id, tag_id);


--
-- TOC entry 2735 (class 2606 OID 16849)
-- Name: roles role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT role_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2731 (class 2606 OID 16710)
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- TOC entry 2733 (class 2606 OID 16712)
-- Name: users user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2736 (class 2606 OID 16713)
-- Name: news_author foreign_key_author_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_author
    ADD CONSTRAINT foreign_key_author_id FOREIGN KEY (author_id) REFERENCES public.author(id) ON DELETE CASCADE;


--
-- TOC entry 2737 (class 2606 OID 16718)
-- Name: news_author foreign_key_news_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_author
    ADD CONSTRAINT foreign_key_news_id FOREIGN KEY (news_id) REFERENCES public.news(id) ON DELETE CASCADE;


--
-- TOC entry 2738 (class 2606 OID 16723)
-- Name: news_tag foreign_key_news_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_tag
    ADD CONSTRAINT foreign_key_news_id FOREIGN KEY (news_id) REFERENCES public.news(id) ON DELETE CASCADE;


--
-- TOC entry 2739 (class 2606 OID 16728)
-- Name: news_tag foreign_key_tag_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_tag
    ADD CONSTRAINT foreign_key_tag_id FOREIGN KEY (tag_id) REFERENCES public.tag(id) ON DELETE CASCADE;


--
-- TOC entry 2740 (class 2606 OID 16843)
-- Name: roles foreign_key_users_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT foreign_key_users_id FOREIGN KEY (user_id) REFERENCES public.users(id) NOT VALID;


-- Completed on 2020-02-09 17:31:58

--
-- PostgreSQL database dump complete
--

