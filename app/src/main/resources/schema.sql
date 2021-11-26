-- SCHEMA: public

-- DROP SCHEMA public ;

CREATE SCHEMA IF NOT EXISTS public
  AUTHORIZATION db_admin;

COMMENT ON SCHEMA public
  IS 'standard public schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO db_admin;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- Table: public.users

-- DROP TABLE public.userts;

CREATE TABLE IF NOT EXISTS public.users
(
  id uuid NOT NULL DEFAULT uuid_generate_v4(),
  name character varying(128) COLLATE pg_catalog."default" NOT NULL,
  created_at timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_id_on_users PRIMARY KEY (id)
)

  TABLESPACE pg_default;

ALTER TABLE public.users
  OWNER to db_admin;
