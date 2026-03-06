CREATE TABLE computer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(19, 2),
    description VARCHAR(500),
    image LONGBLOB,
    manufacture_id BIGINT,
    
    -- Các cột kế thừa từ BaseEntity
    version BIGINT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    modified_by VARCHAR(255),

    CONSTRAINT fk_computer_manufacture FOREIGN KEY (manufacture_id) REFERENCES manufacture(id)
);