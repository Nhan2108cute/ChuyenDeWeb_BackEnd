CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100),
                       phone VARCHAR(20),
                       birthday DATE
);
CREATE TABLE image (
                    id bigint(20) NOT NULL AUTO_INCREMENT,
                    url varchar(255) NOT NULL,
                    public_id varchar(255) NOT NULL,
                    PRIMARY KEY (`id`)
)
ALTER TABLE users ADD COLUMN account_type INT DEFAULT 1;
UPDATE users SET account_type = 0 WHERE username = 'admin';
UPDATE users SET account_type = 2 WHERE username = 'nhancute2108';
-- accountType số 0 là Admin
-- accountType số 1 là Tài khoản thường
-- accountType số 2 là tài khoản premium

