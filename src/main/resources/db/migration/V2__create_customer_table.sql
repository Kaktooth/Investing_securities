CREATE TABLE customer
(
    id               UUID PRIMARY KEY,
    name             VARCHAR(64),
    type_of_property VARCHAR(20),
    address          VARCHAR(100),
    phone_number     VARCHAR(11)
);