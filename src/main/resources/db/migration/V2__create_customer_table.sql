CREATE TABLE customer
(
    id             UUID PRIMARY KEY,
    name           VARCHAR(64),
    typeOfProperty VARCHAR(20),
    address        VARCHAR(100),
    phoneNumber    VARCHAR(11)
);