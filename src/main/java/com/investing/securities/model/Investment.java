package com.investing.securities.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Investment {
    UUID id;
    UUID securitiesId;
    UUID customerId;
    BigInteger financialQuotation;
    LocalDateTime dateOfPurchase;
    LocalDateTime dateOfSale;
}
