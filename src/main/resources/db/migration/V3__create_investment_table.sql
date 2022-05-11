CREATE TABLE investment
(
    id                  UUID PRIMARY KEY,
    financial_quotation BIGINT,
    date_of_purchase    TIMESTAMP,
    date_of_sale        TIMESTAMP,
    securities_id       UUID REFERENCES "securities" ON DELETE CASCADE,
    customer_id         UUID REFERENCES "customer" ON DELETE CASCADE
);