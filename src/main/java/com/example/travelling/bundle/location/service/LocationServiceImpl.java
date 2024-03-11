package com.example.travelling.bundle.location.service;

import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.city.service.CityService;
import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.country.service.CountryService;
import com.example.travelling.bundle.location.domain.Location;
import com.example.travelling.bundle.location.data.LocationData;
import com.example.travelling.bundle.location.data.LocationJpaRepository;
import com.example.travelling.bundle.province.domain.Province;
import com.example.travelling.bundle.province.service.ProvinceService;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.core.domain.appuser.service.AppUserService;
import com.example.travelling.infra.security.exception.UnAuthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final PlatformSecurityContext platformSecurityContext;

    private final CityService cityService;
    private final CountryService countryService;
    private final ProvinceService provinceService;
    private final LocationJpaRepository repository;
    private final AppUserService appUserService;

    @Override
    public List<City> findCitiesByProvinceId(Long provinceId) {
        return cityService.findAllByProvinceId(provinceId);
    }

    @Override
    public List<Province> findProvincesByCountryId(Long countryId) {
        return provinceService.findAllByCountryId(countryId);
    }

    @Override
    public List<Country> findAllCountries() {
        return countryService.findAll();
    }

    @Override
    public void create(LocationData locationData, AppUser user) {
        Country fromCountryId = countryService.findById(locationData.getFromCountryId());
        Province fromProvinceId = provinceService.findById(locationData.getFromProvinceId());
        City fromCityId = cityService.findById(locationData.getFromCityId());
        Country toCountryId = countryService.findById(locationData.getToCountryId());
        Province toProvinceId = provinceService.findById(locationData.getToProvinceId());
        City toCityId = cityService.findById(locationData.getToCityId());
        Date departureDate = locationData.getDepartureDate();
        String departureTime = locationData.getDepartureTime();
        Long passengers = locationData.getPassengers();
        String description = locationData.getDescription();
        AppUser appUser = user;

        Location location = new Location(fromCountryId, fromProvinceId, fromCityId, toCountryId,
                toProvinceId, toCityId, departureDate, departureTime, passengers, description, appUser);
        repository.save(location);
    }

    @Override
    public LocationData findLocationByIdAndAppUser(Long locationId) throws UnAuthenticatedUserException,
            AppUserExceptionError {
        AppUser appUser =
                appUserService.getByUsername(this.platformSecurityContext.authenticatedUser().getUsername());
        Location location = repository.findLocationByIdAndAppUser_Id(locationId, appUser.getId());
        return new LocationData(location, appUser);
    }

    @Override
    public List<Location> findLocationsByIdAndAppUser() throws UnAuthenticatedUserException, AppUserExceptionError {
        AppUser appUser =
                appUserService.getByUsername(this.platformSecurityContext.authenticatedUser().getUsername());
        return repository.findLocationsByAppUser_Id(appUser.getId());
    }

    @Override
    public void removeLocationById(Long locationId) throws UnAuthenticatedUserException, AppUserExceptionError {
        AppUser appUser =
                appUserService.getByUsername(this.platformSecurityContext.authenticatedUser().getUsername());
        Location loc = repository.findLocationByIdAndAppUser_Id(locationId, appUser.getId());

        repository.delete(loc);
    }

}
