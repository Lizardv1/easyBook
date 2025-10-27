--liquibase formatted sql
--changeset virchenko:003

CREATE TABLE IF NOT EXISTS customers (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(32),
    password VARCHAR(255) NOT NULL
);