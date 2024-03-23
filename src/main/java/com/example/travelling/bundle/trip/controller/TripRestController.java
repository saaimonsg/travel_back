package com.example.travelling.bundle.trip.controller;

import com.example.travelling.bundle.trip.data.TripApiConstants;
import com.example.travelling.bundle.trip.service.TripService;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/trip")
public class TripRestController {
    private final PlatformSecurityContext platformSecurityContext;

    private final TripService tripService;
    private final Gson gson = new Gson();


    @RequestMapping(value = "/getAll", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    
    public String getAllTripsDatas() throws UnauthenticatedUserException, AppUserExceptionError {
         this.platformSecurityContext.authenticatedUser().hasPermission(TripApiConstants.READ_TRIPS_PERMISSION);

        return gson.toJson(tripService.findAllTrips());
    }



    @RequestMapping(value = "/get/{tripId}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTripData(@PathVariable(name = "tripId") Long tripId) throws UnauthenticatedUserException,
            AppUserExceptionError {
        this.platformSecurityContext.authenticatedUser().hasPermission(TripApiConstants.READ_TRIPS_PERMISSION);

        return gson.toJson(tripService.findTripById(tripId));
    }


}
