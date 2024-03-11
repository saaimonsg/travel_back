package com.example.travelling.bundle.location.controller;

import com.example.travelling.bundle.location.service.LocationService;
import com.example.travelling.bundle.location.data.LocationData;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.core.domain.appuser.service.AppUserServiceImpl;
import com.example.travelling.infra.security.exception.UnAuthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/location")
public class LocationRestController {
    private final PlatformSecurityContext platformSecurityContext;

    private final LocationService locationService;
    private final AppUserServiceImpl appUserService;
    private final Gson gson = new Gson();


    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody //TODO migrate this to their current controllers country, province and city
    public String findByCountryId(@RequestParam(value = "countryId", required = false) Long countryId,
                                  @RequestParam(value = "provinceId", required = false) Long provinceId,
                                  @RequestParam(value = "cityId", required = false) Long cityId) throws UnAuthenticatedUserException {
        this.platformSecurityContext.authenticatedUser();
        if (provinceId != null && provinceId != 0) {
            return gson.toJson(locationService.findCitiesByProvinceId(provinceId));
        } else if (countryId != null && countryId != 0) {
            return gson.toJson(locationService.findProvincesByCountryId(countryId));
        } else {
            return gson.toJson(locationService.findAllCountries());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createLocation(@RequestBody String body) throws UnAuthenticatedUserException, AppUserExceptionError {

        LocationData locationData = gson.fromJson(body, LocationData.class);
        locationService.create(locationData, appUserService.getByUsername(this.platformSecurityContext.authenticatedUser().getUsername()));
        return gson.toJson("location.create.successful");

    }

    @RequestMapping(value = "/get", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getLocation(@RequestParam(name = "locationId") Long locationId) throws UnAuthenticatedUserException, AppUserExceptionError {

        return gson.toJson(locationService.findLocationByIdAndAppUser(locationId));
//        return gson.toJson(jdbcTemplate.findLocationsByAppUserId(appUser));
    }
    @RequestMapping(value = "/getAll", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getLocations() throws UnAuthenticatedUserException, AppUserExceptionError {
        return gson.toJson(locationService.findLocationsByIdAndAppUser());
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String editLocation(@RequestParam(name = "locationId") Long locationId) throws UnAuthenticatedUserException, AppUserExceptionError {

        return gson.toJson(locationService.findLocationByIdAndAppUser(locationId));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteLocation(@RequestParam(name = "locationId",value = "locationId") Long locationId) throws UnAuthenticatedUserException, AppUserExceptionError {
        locationService.removeLocationById(locationId);
        return gson.toJson("location.delete.successful");
    }

}
