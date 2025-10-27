--liquibase formatted sql
--changeset virchenko:002

CREATE TABLE IF NOT EXISTS rooms (
  id BIGSERIAL PRIMARY KEY,
  hotel_id BIGINT REFERENCES hotels (id) ON DELETE CASCADE NOT NULL,
  "number" VARCHAR(50) NOT NULL,
  "type" VARCHAR(50),
  description TEXT NOT NULL,
  capacity INT CHECK (capacity > 0)
);