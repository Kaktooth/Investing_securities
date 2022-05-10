package com.investing.securities.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String massage) {
        super(massage);
    }
}
