package com.example.travelling.infra.security.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class InvalidTenantIdentiferException extends Throwable {
    public InvalidTenantIdentiferException(final String message) {
        super(message);
    }

    public InvalidTenantIdentiferException(String message, EmptyResultDataAccessException e) {
        super(message, e);
    }
}
