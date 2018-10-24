CREATE SCHEMA IF NOT EXISTS core;
CREATE SCHEMA IF NOT EXISTS security;

CREATE TABLE IF NOT EXISTS core.ip2location_db3 (
  ip_from      bigint                 NOT NULL,
  ip_to        bigint                 NOT NULL,
  country_code character(2)           NOT NULL,
  country_name character varying(64)  NOT NULL,
  region_name  character varying(128) NOT NULL,
  city_name    character varying(128) NOT NULL,
  CONSTRAINT ip2location_db3_pkey PRIMARY KEY (ip_from, ip_to)
);

--COPY core.ip2location_db3 FROM '/Users/ivan/IdeaProjects/tst/pushapp/src/main/resources/IP2LOCATION-LITE-DB3.CSV' WITH CSV QUOTE AS '"';

-- ALTER TABLE core.ip2location_db3
--   ADD COLUMN IF NOT EXISTS id SERIAL;
--
-- update core.ip2location_db3
-- set city_name = 'St Petersburg'
-- where city_name = 'Saint Petersburg';


CREATE TABLE IF NOT EXISTS core.category (
  id          serial primary key,
  name        character varying(128),
  description text
);

ALTER TABLE only core.category
  DROP CONSTRAINT IF EXISTS unique_cat_name;
alter table only core.category
  add constraint unique_cat_name unique (name);

CREATE TABLE IF NOT EXISTS core.subscription (
  id                serial primary key,
  endpoint          text not null,
  expiration_time   timestamp,
  p256dh            text,
  auth              text,
  source_url        text,
  ip                character varying(128),
  country_code      character(2),
  country_name      character varying(64),
  city_name         character varying(128),
  region_name       character varying(128),
  ip2location_id    bigint,
  brw_name          character varying(128),
  brw_major_version character varying(64),
  brw_full_version  character varying(64),
  brw_language      character varying(128),
  landing_language  character varying(64),
  os_name           character varying(64),
  os_version        character varying(64),
  created_ts        timestamp default now()
);

-- ALTER TABLE core.subscription
--   ADD COLUMN IF NOT EXISTS os_name character varying(64);
-- ALTER TABLE core.subscription
--   ADD COLUMN IF NOT EXISTS os_version character varying(64);

CREATE TABLE IF NOT EXISTS core.payload (
  id                  serial primary key,
  title               text,
  body                text,
  icon                text,
  image               text,
  badge               text,
  vibrate             text,
  sound               text,
  dir                 character varying(8),
  tag                 character varying(64),
  data                text,
  require_interaction boolean,
  renotify            boolean,
  silent              boolean,
  actions             text,
  timestamp           timestamp,
  offer_url           text,
  offer_id            text,
  account_name        text,
  sub_total           bigint,
  created_by          character varying(128),
  created_ts          timestamp default now()
);

CREATE TABLE IF NOT EXISTS core.subscription_category_ref (
  subscription_id bigint not null,
  category_id     bigint not null
);


CREATE TABLE IF NOT EXISTS security.users (
  login    character varying(128),
  enabled  boolean default true,
  password text,
  constraint pk_users primary key (login)
);

CREATE TABLE IF NOT EXISTS security.roles (
  id   serial primary key,
  name character varying(128)
);

CREATE TABLE IF NOT EXISTS security.authorities (
  login   character varying(128),
  role_id bigint
);


INSERT INTO security.roles
(name)
  SELECT 'ADMINISTRATOR'
  WHERE
    NOT EXISTS(SELECT id
               FROM security.roles
               WHERE name = 'ADMINISTRATOR');

INSERT INTO security.roles
(name)
  SELECT 'MANAGER'
  WHERE
    NOT EXISTS(SELECT id
               FROM security.roles
               WHERE name = 'MANAGER');


INSERT INTO core.category
(name, description)
  SELECT
    'Sport',
    'Everything that has connection to sport'
  WHERE
    NOT EXISTS(SELECT id
               FROM core.category
               WHERE name = 'Sport' and description = 'Everything that has connection to sport');

INSERT INTO core.category
(name, description)
  SELECT
    'Adult',
    'Everything that has connection to adult'
  WHERE
    NOT EXISTS(SELECT id
               FROM core.category
               WHERE name = 'Adult' and description = 'Everything that has connection to adult');

INSERT INTO core.category
(name, description)
  SELECT
    'Business',
    'Everything that has connection to business'
  WHERE
    NOT EXISTS(SELECT id
               FROM core.category
               WHERE name = 'Business' and description = 'Everything that has connection to business');

INSERT INTO core.category
(name, description)
  SELECT
    'Games',
    'Everything that has connection to games'
  WHERE
    NOT EXISTS(SELECT id
               FROM core.category
               WHERE name = 'Games' and description = 'Everything that has connection to games');

INSERT INTO core.category
(name, description)
  SELECT
    'Health',
    'Everything that has connection to health'
  WHERE
    NOT EXISTS(SELECT id
               FROM core.category
               WHERE name = 'Health' and description = 'Everything that has connection to health');

INSERT INTO core.category
(name, description)
  SELECT
    'Home',
    'Everything that has connection to home'
  WHERE
    NOT EXISTS(SELECT id
               FROM core.category
               WHERE name = 'Home' and description = 'Everything that has connection to home');

INSERT INTO core.category
(name, description)
  SELECT
    'Kids',
    'Everything that has connection to kids or children'
  WHERE
    NOT EXISTS(SELECT id
               FROM core.category
               WHERE name = 'Kids' and description = 'Everything that has connection to kids or children');

INSERT INTO core.category
(name, description)
  SELECT
    'News',
    'Everything that has connection to news'
  WHERE
    NOT EXISTS(SELECT id
               FROM core.category
               WHERE name = 'News' and description = 'Everything that has connection to news');

INSERT INTO core.category
(name, description)
  SELECT
    'Science',
    'Everything that has connection to science'
  WHERE
    NOT EXISTS(SELECT id
               FROM core.category
               WHERE name = 'Science' and description = 'Everything that has connection to science');

INSERT INTO core.category
(name, description)
  SELECT
    'Shopping',
    'Everything that has connection to shopping'
  WHERE
    NOT EXISTS(SELECT id
               FROM core.category
               WHERE name = 'Shopping' and description = 'Everything that has connection to shopping');




INSERT INTO security.users
(login, password)
  SELECT
    'q',
    '$2a$11$0A9CY7KTNfVDG4I8wBUdEun0yXgfLbvKZjn0Qm0dQ74yCtWMQz.tO'
  WHERE
    NOT EXISTS(SELECT login
               FROM security.users
               WHERE login = 'q');


INSERT INTO security.authorities
(login, role_id)
  SELECT
    'q', 1
  WHERE
    NOT EXISTS(SELECT login
               FROM security.authorities
               WHERE login = 'q' and role_id = 1);

INSERT INTO security.authorities
(login, role_id)
  SELECT
    'q', 2
  WHERE
    NOT EXISTS(SELECT login
               FROM security.authorities
               WHERE login = 'q' and role_id = 2);

