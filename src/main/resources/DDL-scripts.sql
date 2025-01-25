CREATE SEQUENCE overlord_aurelia.currency_tier_sequence
START WITH 1
INCREMENT BY 1

CREATE TABLE overlord_aurelia.currency_tier (
    tier_id INT PRIMARY KEY,
    tier_name VARCHAR(10) NOT NULL,
    tier_description TEXT
);

CREATE SEQUENCE overlord_aurelia.currency_sequence
START WITH 1
INCREMENT BY 1

CREATE TABLE overlord_aurelia.currency_details (
    currency_id INT PRIMARY KEY,
    currency_name VARCHAR(50) NOT NULL,
    currency_tier VARCHAR(10) NOT NULL,
    tier_id INT,
    FOREIGN KEY (tier_id) REFERENCES overlord_aurelia.currency_tier (tier_id)
);

CREATE SEQUENCE overlord_aurelia.user_sequence
START WITH 1
INCREMENT BY 1

CREATE TABLE overlord_aurelia.user_details (
    user_id INT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    xp INT DEFAULT 0,
    level INT DEFAULT 1,
    created_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE overlord_aurelia.user_balance_sequence
START WITH 1
INCREMENT BY 1

CREATE TABLE overlord_aurelia.user_balance (
    id INT PRIMARY KEY,
    user_id INT NOT NULL,
    currency_id INT NOT NULL,
    balance INT DEFAULT 0,
    created_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES overlord_aurelia.user_details (user_id),
    FOREIGN KEY (currency_id) REFERENCES overlord_aurelia.currency_details (currency_id)
);