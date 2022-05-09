CREATE TABLE securities
(
    id                       UUID PRIMARY KEY,
    minAmountTransaction     INTEGER,
    rating                   INTEGER,
    profitabilityForLastYear INTEGER,
    additionalInfo           VARCHAR(255)
);