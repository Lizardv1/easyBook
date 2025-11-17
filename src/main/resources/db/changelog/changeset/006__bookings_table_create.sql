--liquibase formatted sql
--changeset virchenko:006

CREATE TABLE IF NOT EXISTS bookings (
    id BIGSERIAL PRIMARY KEY,
    room_id BIGINT  REFERENCES rooms(id) ON DELETE CASCADE NOT NULL,
    customer_id BIGINT REFERENCES customers(id) ON DELETE CASCADE NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_date_range CHECK (check_out_date > check_in_date)
);