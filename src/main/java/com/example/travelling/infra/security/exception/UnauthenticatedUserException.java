package com.example.travelling.infra.security.exception;

public class UnauthenticatedUserException extends Throwable {

    public UnauthenticatedUserException(String message) {
        super(message);
    }
}
