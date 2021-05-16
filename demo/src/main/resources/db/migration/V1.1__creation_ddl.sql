CREATE TABLE IF NOT EXISTS public.form (

    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(200) NOT NULL,
    agree_terms boolean NOT NULL,
    create_date timestamp ,
    update_date timestamp
);

CREATE TABLE IF NOT EXISTS public.sector (

    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(200) NOT NULL,
    parent_id bigint
);

CREATE TABLE IF NOT EXISTS public.form_sector (

    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sector_id bigint NOT NULL ,
    form_id bigint NOT NULL,
    CONSTRAINT FK_form_id FOREIGN KEY (form_id)
    REFERENCES form(id),
    CONSTRAINT FK_sector_id FOREIGN KEY (sector_id)
    REFERENCES sector(id)
);


CREATE SEQUENCE IF NOT EXISTS public.form_id_seq
    MINVALUE 1
    MAXVALUE 99999999999999999
    START WITH 1
    INCREMENT BY 1
    CACHE 10;

CREATE SEQUENCE IF NOT EXISTS public.form_sector_id_seq
    MINVALUE 1
    MAXVALUE 99999999999999999
    START WITH 1
    INCREMENT BY 1
    CACHE 10;