package com.investing.securities.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    UUID id;
    String name;
    String typeOfProperty;
    String address;
    String phoneNumber;
}
