DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR (255) NOT NULL,
    last_name VARCHAR (255) NOT NULL,
    created_at DATE NOT NULL,
    is_deleted BOOLEAN NOT NULL,
    role VARCHAR (255) NOT NULL
);

INSERT INTO users (first_name, last_name, created_at, is_deleted, role) VALUES
    ('Admin', 'Admin', NOW(), false, 'ADMIN'),
    ('User', 'User', NOW(), false, 'USER');