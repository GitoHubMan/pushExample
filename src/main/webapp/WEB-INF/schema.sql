CREATE SCHEMA IF NOT EXISTS core;
CREATE SCHEMA IF NOT EXISTS security;


CREATE TABLE IF NOT EXISTS core.category (
id serial primary key,
name character varying(128),
description text
);

ALTER TABLE only core.category DROP CONSTRAINT IF EXISTS unique_cat_name;
alter table only core.category add constraint unique_cat_name unique (name);

CREATE TABLE IF NOT EXISTS core.subscription (
id serial primary key,
endpoint text not null,
expiration_time timestamp,
p256dh text,
auth text,
source_url text,
browser_id bigint,
landing_language character varying(64),
created_ts timestamp default now()
);

-- CREATE INDEX IF NOT EXISTS subscription_id_idx ON films (id);


CREATE TABLE IF NOT EXISTS core.subscription_category_ref (
subscription_id bigint not null,
category_id bigint not null
);


CREATE TABLE IF NOT EXISTS core.browser (
id serial primary key,
name character varying(128),
major_version character varying(64),
full_version character varying(64),
language character varying(64)
);

ALTER TABLE only core.browser DROP CONSTRAINT IF EXISTS unique_brw_name_version;
alter table only core.browser add constraint unique_brw_name_version unique (name, major_version, full_version);


CREATE TABLE IF NOT EXISTS security.users (
login character varying(128),
enabled boolean default true,
password text,
constraint pk_users primary key (login)
);

CREATE TABLE IF NOT EXISTS security.roles (
id serial primary key,
name character varying(128)
);

CREATE TABLE IF NOT EXISTS security.authorities (
login character varying(128),
role_id bigint
);


INSERT INTO security.roles
(name)
  SELECT 'ADMINISTRATOR'
  WHERE
    NOT EXISTS (SELECT id FROM security.roles WHERE name = 'ADMINISTRATOR');

INSERT INTO security.roles
(name)
  SELECT 'MANAGER'
  WHERE
    NOT EXISTS (SELECT id FROM security.roles WHERE name = 'MANAGER');



INSERT INTO core.category
(name, description)
  SELECT 'Sport', 'Everything that has connection to sport'
  WHERE
    NOT EXISTS (SELECT id FROM core.category WHERE name = 'Sport' and description = 'Everything that has connection to sport');

INSERT INTO core.category
(name, description)
  SELECT 'Adult', 'Everything that has connection to adult'
  WHERE
    NOT EXISTS (SELECT id FROM core.category WHERE name = 'Adult' and description = 'Everything that has connection to adult');

INSERT INTO core.category
(name, description)
  SELECT 'Business', 'Everything that has connection to business'
  WHERE
    NOT EXISTS (SELECT id FROM core.category WHERE name = 'Business' and description = 'Everything that has connection to business');

INSERT INTO core.category
(name, description)
  SELECT 'Games', 'Everything that has connection to games'
  WHERE
    NOT EXISTS (SELECT id FROM core.category WHERE name = 'Games' and description = 'Everything that has connection to games');

INSERT INTO core.category
(name, description)
  SELECT 'Health', 'Everything that has connection to health'
  WHERE
    NOT EXISTS (SELECT id FROM core.category WHERE name = 'Health' and description = 'Everything that has connection to health');

INSERT INTO core.category
(name, description)
  SELECT 'Home', 'Everything that has connection to home'
  WHERE
    NOT EXISTS (SELECT id FROM core.category WHERE name = 'Home' and description = 'Everything that has connection to home');

INSERT INTO core.category
(name, description)
  SELECT 'Kids', 'Everything that has connection to kids or children'
  WHERE
    NOT EXISTS (SELECT id FROM core.category WHERE name = 'Kids' and description = 'Everything that has connection to kids or children');

INSERT INTO core.category
(name, description)
  SELECT 'News', 'Everything that has connection to news'
  WHERE
    NOT EXISTS (SELECT id FROM core.category WHERE name = 'News' and description = 'Everything that has connection to news');

INSERT INTO core.category
(name, description)
  SELECT 'Science', 'Everything that has connection to science'
  WHERE
    NOT EXISTS (SELECT id FROM core.category WHERE name = 'Science' and description = 'Everything that has connection to science');

INSERT INTO core.category
(name, description)
  SELECT 'Shopping', 'Everything that has connection to shopping'
  WHERE
    NOT EXISTS (SELECT id FROM core.category WHERE name = 'Shopping' and description = 'Everything that has connection to shopping');

