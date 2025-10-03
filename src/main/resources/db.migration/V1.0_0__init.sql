-- V1__create_tables.sql

CREATE TABLE IF NOT EXISTS restaurant (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description TEXT,
                            category VARCHAR(100),
                            has_delivery BOOLEAN DEFAULT FALSE,
                            contact VARCHAR(100),
                            street VARCHAR(255),
                            city VARCHAR(100),
                            state VARCHAR(100),
                            zip_code VARCHAR(20),
                            country VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS dish (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      description TEXT,
                      price DECIMAL(10, 2) NOT NULL,
                      restaurant_id BIGINT NOT NULL,
                      CONSTRAINT fk_dish_restaurant
                          FOREIGN KEY (restaurant_id)
                              REFERENCES restaurant(id)
                              ON DELETE CASCADE
);

CREATE INDEX idx_dish_restaurant_id ON dish(restaurant_id);