--liquibase formatted sql
--changeset virchenko:001

CREATE TABLE IF NOT EXISTS addresses (
  id SERIAL PRIMARY KEY,
  country VARCHAR(2) NOT NULL,
  city VARCHAR(128) NOT NULL,
  street VARCHAR(254) NOT NULL,
  building VARCHAR(254) NOT NULL,
  zip INT NOT NULL
);