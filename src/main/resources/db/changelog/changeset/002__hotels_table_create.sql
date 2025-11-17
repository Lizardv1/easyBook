--liquibase formatted sql
--changeset virchenko:002

CREATE TABLE IF NOT EXISTS hotels (
  id SERIAL PRIMARY KEY,
  address_id INTEGER REFERENCES addresses (id) ON DELETE CASCADE NOT NULL,
  name VARCHAR(200) NOT NULL,
  description TEXT NOT NULL,
  phone VARCHAR(50),
  email VARCHAR(128) NOT NULL,
  options INT[],
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);