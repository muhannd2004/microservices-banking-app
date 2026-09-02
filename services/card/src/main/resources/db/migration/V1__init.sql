CREATE SEQUENCE card_number_seq
    START WITH 100000000
    MAXVALUE 999999999
    INCREMENT BY 1
    NO CYCLE;

CREATE TABLE IF NOT EXISTS card (
    card_id  BIGSERIAL     PRIMARY KEY,
    card_number  BIGINT        NOT NULL,
    token_id     VARCHAR(36)   NOT NULL,
    account_id   BIGINT        NOT NULL,
    card_type    VARCHAR(20)   NOT NULL,
    card_status  VARCHAR(20)   NOT NULL,
    pin          VARCHAR(60)   NOT NULL,
    cvv          VARCHAR(60)   NOT NULL,
    expiry_date  DATE          NOT NULL,
    daily_limit  NUMERIC(15,2) NOT NULL,
    daily_spent  NUMERIC(15,2) NOT NULL DEFAULT 0,
    created_at   TIMESTAMP     NOT NULL,
    created_by   VARCHAR(50)   NOT NULL,
    updated_at   TIMESTAMP,
    updated_by   VARCHAR(50)
    );

ALTER SEQUENCE card_card_id_seq RESTART WITH 4000000000000000;
