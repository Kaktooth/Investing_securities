package com.investing.securities.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Securities {
    UUID id;
    int minAmountTransaction;
    int rating;
    int profitabilityForLastYear;
    String additionalInfo;
}
