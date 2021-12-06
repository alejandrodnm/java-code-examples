CREATE TABLE public.users
(
  id SERIAL,
  name character varying(128) COLLATE pg_catalog."default" NOT NULL,
  created_at timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_id_on_users PRIMARY KEY (id)
)
