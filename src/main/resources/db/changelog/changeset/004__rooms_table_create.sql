--liquibase formatted sql
--changeset virchenko:004

CREATE TABLE IF NOT EXISTS rooms (
  id BIGSERIAL PRIMARY KEY,
  hotel_id INTEGER REFERENCES hotels (id) ON DELETE CASCADE NOT NULL,
  "number" VARCHAR(50) NOT NULL,
  type INT REFERENCES room_types(id),
  description TEXT NOT NULL,
  capacity INT CHECK (capacity > 0),
  options INT[]
);