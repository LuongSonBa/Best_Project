-- =======================
-- Table: Manufacture
-- =======================
CREATE TABLE manufacture (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(50),
    email VARCHAR(255),
    description TEXT
);

-- =======================
-- Table: Product
-- =======================
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(180) NOT NULL,
    price DECIMAL(15,2) NOT NULL,
    description VARCHAR(1000),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    manufacture_id BIGINT,
    CONSTRAINT fk_product_manufacture
        FOREIGN KEY (manufacture_id) REFERENCES manufacture(id)
        ON DELETE SET NULL
);

-- =======================
-- Table: Users
-- =======================
CREATE TABLE users.users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER'
);
