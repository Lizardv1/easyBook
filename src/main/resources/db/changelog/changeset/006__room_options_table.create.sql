CREATE TABLE IF NOT EXISTS room_options (
  id SERIAL PRIMARY KEY,
  name VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS room_to_option (
  room_id BIGINT NOT NULL REFERENCES rooms(id),
  option_id INTEGER NOT NULL REFERENCES room_options(id)
);

