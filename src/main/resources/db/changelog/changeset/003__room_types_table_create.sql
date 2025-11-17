--liquibase formatted sql
--changeset virchenko:003

CREATE TABLE IF NOT EXISTS room_types (
  id SERIAL PRIMARY KEY,
  type VARCHAR(36) NOT NULL UNIQUE,
  description TEXT
);