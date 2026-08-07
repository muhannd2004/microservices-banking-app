CREATE TABLE IF NOT EXISTS customer (
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    mobile_number VARCHAR(20)  NOT NULL UNIQUE,
    token_id      VARCHAR(36)  NOT NULL UNIQUE,
    created_at    TIMESTAMP    NOT NULL,
    created_by    VARCHAR(50)  NOT NULL,
    updated_at    TIMESTAMP,
    updated_by    VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS account (
    account_number BIGSERIAL PRIMARY KEY,
    customer_id    BIGINT       NOT NULL REFERENCES customer (id),
    account_type   VARCHAR(50)  NOT NULL,
    branch_address VARCHAR(200) NOT NULL,
    communication_sw BOOLEAN    DEFAULT TRUE,
    created_at     TIMESTAMP    NOT NULL,
    created_by     VARCHAR(50)  NOT NULL,
    updated_at     TIMESTAMP,
    updated_by     VARCHAR(50)
);

ALTER SEQUENCE account_account_number_seq RESTART WITH 1000000;
