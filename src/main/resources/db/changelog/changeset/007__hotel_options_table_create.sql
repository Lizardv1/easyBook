--liquibase formatted sql
--changeset virchenko:007

CREATE TABLE IF NOT EXISTS hotel_options (
  id SERIAL PRIMARY KEY,
  name VARCHAR(128) NOT NULL
);

