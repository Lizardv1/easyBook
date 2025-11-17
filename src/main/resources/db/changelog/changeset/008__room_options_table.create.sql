--liquibase formatted sql
--changeset virchenko:008

CREATE TABLE IF NOT EXISTS room_options (
  id SERIAL PRIMARY KEY,
  name VARCHAR(128) NOT NULL
);

