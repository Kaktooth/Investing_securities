CREATE TABLE securities
(
    id                          UUID PRIMARY KEY,
    ISIN                        VARCHAR(12),
    min_amount_transaction      INTEGER,
    rating                      INTEGER,
    profitability_for_last_year INTEGER,
    additional_info             VARCHAR(255)
);