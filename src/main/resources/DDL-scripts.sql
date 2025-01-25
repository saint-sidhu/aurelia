CREATE TABLE IF NOT EXISTS overlord_aurelia.currency_details (
    currency_id SERIAL PRIMARY KEY,
    currency_name VARCHAR(255),
    currency_rarity VARCHAR(255),
    currency_tier VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS overlord_aurelia.user_balance (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    currency_id INT NOT NULL UNIQUE,
    balance INT,
    created_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES overlord_aurelia.user_details(user_id) ON DELETE CASCADE,
    FOREIGN KEY (currency_id) REFERENCES overlord_aurelia.currency_details(currency_id) ON DELETE CASCADE
);

-- Trigger function to auto-update updated_date_time
CREATE OR REPLACE FUNCTION overlord_aurelia.update_updated_date_time()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_date_time = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TABLE IF NOT EXISTS overlord_aurelia.user_progression (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    xp INT,
    level INT,
    FOREIGN KEY (user_id) REFERENCES overlord_aurelia.user_details(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS overlord_aurelia.user_details (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(255),
    created_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Trigger function to auto-update updated_date_time
CREATE OR REPLACE FUNCTION overlord_aurelia.update_user_details_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_date_time = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
