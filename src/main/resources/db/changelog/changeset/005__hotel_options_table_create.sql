CREATE TABLE IF NOT EXISTS hotel_options (
  id SERIAL PRIMARY KEY,
  name VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS hotel_to_option (
  hotel_id BIGINT NOT NULL REFERENCES hotels(id),
  option_id INTEGER NOT NULL REFERENCES hotel_options(id)
);

