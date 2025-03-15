CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE accounts (
                          id SERIAL PRIMARY KEY,
                          type VARCHAR(50) NOT NULL,
                          balance NUMERIC(15, 2) NOT NULL,
                          user_id INTEGER NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

ALTER TABLE accounts ADD COLUMN currency VARCHAR(3) DEFAULT 'UAH';