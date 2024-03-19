package com.example.travelling.infra.core.domain.appuser.exception;

public class AppUserExceptionError extends Exception {
    public AppUserExceptionError(String message) {
        super(message);
    }

    public AppUserExceptionError(String amountPassengers, String amountPassengersIsRequired) {
        super(amountPassengers + " " + amountPassengersIsRequired);
    }
}
