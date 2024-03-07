package com.example.travelling.infra.security.exception;

public class NoAuthorizationException extends RuntimeException {

    public NoAuthorizationException(final String message) {
        super(message);
    }
}
