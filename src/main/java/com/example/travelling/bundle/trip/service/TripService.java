package com.example.travelling.bundle.trip.service;

import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.trip.domain.Trip;
import com.example.travelling.bundle.trip.data.TripData;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;

import java.util.List;

public interface TripService {
    List<TripData> findAllTrips() throws AppUserExceptionError;
    TripData findTripById(Long tripId) throws AppUserExceptionError;
}
