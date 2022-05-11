CREATE TABLE investment
(
    id                  UUID PRIMARY KEY,
    financial_quotation bigint,
    date_of_purchase    timestamp,
    date_of_sale        timestamp,
    securities_id       UUID REFERENCES "securities" ON DELETE CASCADE,
    customer_id         UUID REFERENCES "customer" ON DELETE CASCADE
);