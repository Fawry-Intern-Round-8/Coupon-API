CREATE TYPE discount_type AS ENUM ('FIXED', 'PERCENTAGE');

CREATE TABLE coupon (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR NOT NULL UNIQUE,
    discount_type discount_type NOT NULL,
    value INTEGER NOT NULL,
    max_usages INTEGER NOT NULL,
    current_usages INTEGER NOT NULL DEFAULT 0,
    expiry_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE coupon_consumption (
    id BIGSERIAL PRIMARY KEY,
    coupon_id BIGSERIAL NOT NULL REFERENCES coupon(id),
    order_id VARCHAR NOT NULL,
    customer_email VARCHAR NOT NULL,
    applied_value INTEGER NOT NULL,
    consumed_at TIMESTAMP NOT NULL DEFAULT NOW()
);
