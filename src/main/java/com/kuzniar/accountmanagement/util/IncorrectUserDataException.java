package com.kuzniar.accountmanagement.util;

public class IncorrectUserDataException extends RuntimeException {

    public IncorrectUserDataException(String message) {
        super(message);
    }
}
