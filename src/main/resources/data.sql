DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR (255) NOT NULL,
    last_name VARCHAR (255) NOT NULL,
    created_at DATE NOT NULL,
    role VARCHAR (255) NOT NULL
);

INSERT INTO users (first_name, last_name, created_at, role) VALUES
    ('Admin', 'Admin', NOW(), 'ROLE_ADMIN'),
    ('User', 'User', NOW(), 'ROLE_USER');