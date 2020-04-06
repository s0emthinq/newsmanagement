CREATE TABLE public.author (
    id bigserial NOT NULL,
    name character varying(30) NOT NULL,
    surname character varying(30) NOT NULL
);

CREATE TABLE public.news (
    id bigserial NOT NULL,
    title character varying(30) NOT NULL,
    short_text character varying(100) NOT NULL,
    full_text character varying(2000) NOT NULL,
    creation_date date NOT NULL,
    modification_date date NOT NULL
);

CREATE TABLE public.news_author (
    news_id bigint NOT NULL,
    author_id bigint NOT NULL
);

CREATE TABLE public.news_tag (
    news_id bigint NOT NULL,
    tag_id bigint NOT NULL
);

CREATE TABLE public.roles (
    user_id bigint NOT NULL,
    role_name character varying(30) NOT NULL
);

CREATE TABLE public.tag (
    id bigserial NOT NULL,
    name character varying(30) NOT NULL
);

CREATE TABLE public.users (
    id bigserial NOT NULL,
    name character varying(20) NOT NULL,
    surname character varying(20) NOT NULL,
    login character varying(30) NOT NULL,
    password character varying(30) NOT NULL
);

ALTER TABLE public.news_author
    ADD CONSTRAINT news_author_pkey PRIMARY KEY (news_id, author_id);

ALTER TABLE public.news_tag
    ADD CONSTRAINT news_tag_pkey PRIMARY KEY (news_id, tag_id);

ALTER TABLE public.news_author
    ADD CONSTRAINT foreign_key_author_id FOREIGN KEY (author_id) REFERENCES public.author(id) ON DELETE CASCADE;

ALTER TABLE public.news_author
    ADD CONSTRAINT foreign_key_news_id_from_author FOREIGN KEY (news_id) REFERENCES public.news(id) ON DELETE CASCADE;

ALTER TABLE public.news_tag
    ADD CONSTRAINT foreign_key_news_id_from_tag FOREIGN KEY (news_id) REFERENCES public.news(id) ON DELETE CASCADE;

ALTER TABLE public.news_tag
    ADD CONSTRAINT foreign_key_tag_id FOREIGN KEY (tag_id) REFERENCES public.tag(id) ON DELETE CASCADE;

ALTER TABLE public.roles
    ADD CONSTRAINT foreign_key_users_id FOREIGN KEY (user_id) REFERENCES public.users(id);

