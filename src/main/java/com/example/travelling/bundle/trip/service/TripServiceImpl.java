package com.example.travelling.bundle.trip.service;

import com.example.travelling.infra.core.domain.appuser.data.AppUserData;
import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.city.service.CityService;
import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.country.service.CountryService;
import com.example.travelling.bundle.province.domain.Province;
import com.example.travelling.bundle.trip.data.TripJpaRepository;
import com.example.travelling.bundle.trip.domain.Trip;
import com.example.travelling.bundle.trip.data.TripData;
import com.example.travelling.bundle.province.service.ProvinceService;
import com.example.travelling.infra.core.domain.appuser.exception.AppUserExceptionError;
import com.example.travelling.infra.core.domain.appuser.service.AppUserService;
import com.example.travelling.infra.security.exception.UnauthenticatedUserException;
import com.example.travelling.infra.security.service.PlatformSecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final PlatformSecurityContext platformSecurityContext;
    private final TripJpaRepository repository;

    @Override
    public List<TripData> findAllTrips() throws AppUserExceptionError {
        List<TripData> dataList = new ArrayList<>();

        for (Trip trip : repository.findAll()) {
            dataList.add(new TripData(trip));
        }
        return dataList;
    }

    @Override
    public TripData findTripById(Long tripId) throws AppUserExceptionError {
        Trip trip = repository.findById(tripId).orElseThrow();
        return new TripData(trip);
    }


}
