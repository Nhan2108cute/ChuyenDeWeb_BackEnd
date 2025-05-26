CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100),
                       phone VARCHAR(20),
                       birthday DATE
);
ALTER TABLE users ADD COLUMN account_type INT DEFAULT 1;
UPDATE users SET account_type = 0 WHERE username = 'admin';


