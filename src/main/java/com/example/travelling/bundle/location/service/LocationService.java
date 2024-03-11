package com.example.travelling.bundle.location.service;

import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.location.domain.Location;
import com.example.travelling.bundle.location.data.LocationData;
import com.example.travelling.bundle.province.domain.Province;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.security.exception.UnAuthenticatedUserException;

import java.util.List;

public interface LocationService {
    List<City> findCitiesByProvinceId(Long provinceId);
    List<Province> findProvincesByCountryId(Long countryId);
    List<Country> findAllCountries();

    void create(LocationData locationData, AppUser user);


    LocationData findLocationByIdAndAppUser(Long locationId) throws UnAuthenticatedUserException, AppUserExceptionError;

    List<Location> findLocationsByIdAndAppUser() throws UnAuthenticatedUserException, AppUserExceptionError;

    void removeLocationById(Long locationId) throws UnAuthenticatedUserException, AppUserExceptionError;
}
