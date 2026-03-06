CREATE TABLE revinfo (
    rev INT NOT NULL AUTO_INCREMENT,
    revtstmp BIGINT NOT NULL,
    username VARCHAR(100),
    PRIMARY KEY (rev)
);

CREATE TABLE manufacture_AUD (
    id BIGINT NOT NULL,
    rev INT NOT NULL,
    revtype TINYINT,

    name VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    description TEXT,

    created_at TIMESTAMP,
    modified_at TIMESTAMP,
    created_by VARCHAR(255),
    modified_by VARCHAR(255),

    PRIMARY KEY (id, rev),
    CONSTRAINT fk_manufacture_aud_rev
        FOREIGN KEY (rev) REFERENCES revinfo (rev)
);


CREATE TABLE product_AUD (
    id BIGINT NOT NULL,
    rev INT NOT NULL,
    revtype TINYINT,

    name VARCHAR(180),
    price DECIMAL(15,2),
    description TEXT,
    active BOOLEAN,

    manufacture_id BIGINT,

    created_at TIMESTAMP,
    modified_at TIMESTAMP,
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    
    PRIMARY KEY (id, rev),
    CONSTRAINT fk_product_aud_rev
        FOREIGN KEY (rev) REFERENCES revinfo (rev)
);