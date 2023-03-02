--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

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
-- Name: all_events; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.all_events (
    id integer NOT NULL,
    type text NOT NULL,
    message text NOT NULL,
    "timestamp" timestamp without time zone NOT NULL
);


ALTER TABLE public.all_events OWNER TO postgres;

--
-- Name: all_events_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.all_events_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.all_events_id_seq OWNER TO postgres;

--
-- Name: all_events_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.all_events_id_seq OWNED BY public.all_events.id;


--
-- Name: all_events id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.all_events ALTER COLUMN id SET DEFAULT nextval('public.all_events_id_seq'::regclass);


--
-- Data for Name: all_events; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.all_events (id, type, message, "timestamp") FROM stdin;
1	PLAY	testmsg	2023-03-02 15:15:23.202
2	PLAY	testmsg	2023-03-02 15:15:25.318
4	PLAY	testmsg	2023-03-02 15:20:43.185
6	PLAY	testmsg	2023-03-02 15:20:43.932
7	PLAY	testmsg	2023-03-02 15:20:44.265
8	PLAY	testmsg	2023-03-02 15:20:44.579
9	PLAY	testmsg	2023-03-02 15:20:44.89
10	PLAY	testmsg	2023-03-02 15:20:45.226
11	PLAY	testmsg	2023-03-02 15:20:45.538
12	PLAY	testmsg	2023-03-02 15:20:45.869
13	PLAY	testmsg	2023-03-02 15:20:46.174
5	PLAY	testmsg	2023-03-01 00:00:00
3	PLAY	testmsg	2023-03-04 00:00:00
14	QUIT	testmsg	2023-03-02 19:13:59.553
15	QUIT	testmsg	2023-03-02 19:14:01.599
16	QUIT	testmsg	2023-03-02 19:21:23.824
\.


--
-- Name: all_events_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.all_events_id_seq', 16, true);


--
-- Name: all_events all_events_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.all_events
    ADD CONSTRAINT all_events_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

