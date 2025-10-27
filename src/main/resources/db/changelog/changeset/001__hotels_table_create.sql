--liquibase formatted sql
--changeset virchenko:001

CREATE TABLE IF NOT EXISTS addresses (
  id BIGSERIAL PRIMARY KEY,
  country VARCHAR(2) NOT NULL,
  city VARCHAR(128) NOT NULL,
  street VARCHAR(254) NOT NULL,
  building VARCHAR(254) NOT NULL,
  zip INT
);

CREATE TABLE IF NOT EXISTS hotels (
  id BIGSERIAL PRIMARY KEY,
  address_id BIGINT REFERENCES addresses (id) ON DELETE CASCADE NOT NULL,
  name VARCHAR(200) NOT NULL,
  description TEXT,
  phone VARCHAR(50),
  email VARCHAR(128),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);