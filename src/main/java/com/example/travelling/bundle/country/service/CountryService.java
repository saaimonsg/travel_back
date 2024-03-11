package com.example.travelling.bundle.country.service;

import com.example.travelling.bundle.country.domain.Country;

import java.util.List;

public interface CountryService {


    List<Country> findAll();

    Country findById(Long toCountryId);

    Country save(Country country);

    Country delete(Long countryId);
}
