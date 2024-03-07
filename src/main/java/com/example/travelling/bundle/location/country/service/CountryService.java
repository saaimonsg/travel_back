package com.example.travelling.bundle.location.country.service;

import com.example.travelling.bundle.location.country.data.CountryData;
import com.example.travelling.bundle.location.country.data.LocationData;

import java.util.List;

public interface CountryService {

    List<CountryData> findAll();

    CountryData findByCountryId(Long countryId);

    LocationData findCityByCountryIdAndProvinceId(Long countryId, Long provinceId);
}
