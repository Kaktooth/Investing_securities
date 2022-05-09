CREATE TABLE investment
(
    id                 UUID PRIMARY KEY,
    financialQuotation bigint,
    dateOfPurchase     timestamp,
    dateOfSale         timestamp,
    securities_id      UUID REFERENCES "securities",
    customer_id        UUID REFERENCES "customer"
);