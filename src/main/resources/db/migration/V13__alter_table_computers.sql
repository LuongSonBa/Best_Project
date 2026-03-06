CREATE TABLE computer_AUD (
    id BIGINT NOT NULL,
    rev INT NOT NULL,
    revtype TINYINT, -- 0: Add, 1: Mod, 2: Del

    name VARCHAR(255),
    price DECIMAL(19, 2),
    description VARCHAR(500),
    manufacture_id BIGINT,

    -- Audit thông tin từ BaseEntity
    version BIGINT,
    created_at TIMESTAMP,
    modified_at TIMESTAMP,
    created_by VARCHAR(255),
    modified_by VARCHAR(255),

    PRIMARY KEY (id, rev),
    CONSTRAINT fk_computer_aud_rev FOREIGN KEY (rev) REFERENCES revinfo (rev)
);

INSERT INTO computer (name, price, description, manufacture_id, version, created_by) VALUES 
('HP Pavilion 15-eg3093TU', 125000.00, 'Ultra-slim office laptop, i5-1335U, 16GB RAM', 2, 0, 'system'),
('HP Victus 15-fa1139TX', 185000.00, 'Entry-level gaming laptop, RTX 3050, 144Hz', 2, 0, 'system'),
('HP EliteBook 840 G10', 250000.00, 'Premium enterprise notebook with advanced security', 2, 0, 'system'),
('HP Envy x360 14', 195000.00, 'Versatile 2-in-1 touchscreen laptop with OLED display', 2, 0, 'system'),
('HP Omen 16-wd0013TX', 320000.00, 'High-end gaming machine, Core i7-13620H, RTX 4050', 2, 0, 'system'),
('HP ProBook 450 G10', 165000.00, 'Reliable business-grade laptop with upgradeable memory', 2, 0, 'system'),
('HP Pavilion x360 14', 145000.00, 'Flexible hinge design for students and creators', 2, 0, 'system'),
('HP ZBook Firefly 14 G10', 285000.00, 'Professional mobile workstation for CAD and design', 2, 0, 'system'),
('HP 15s-fq5229TU', 110000.00, 'Daily essential laptop for home and basic study use', 2, 0, 'system'),
('HP Victus 16-r0129TX', 215000.00, 'Enhanced cooling gaming laptop with 13th Gen Intel', 2, 0, 'system');