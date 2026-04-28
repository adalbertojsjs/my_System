package com.repository.My_System.domain.exceptions;

public class InvalidPaymentExceptions extends RuntimeException {
    public InvalidPaymentExceptions(String message) {
        super(message);
    }
}
