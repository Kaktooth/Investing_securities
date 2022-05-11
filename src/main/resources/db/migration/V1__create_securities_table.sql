CREATE TABLE securities
(
    id                          UUID PRIMARY KEY,
    ISIN                        VARCHAR(12),
    min_amount_transaction      BIGINT,
    rating                      BIGINT,
    profitability_for_last_year BIGINT,
    additional_info             VARCHAR(255)
);