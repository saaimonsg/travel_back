package com.example.travelling.bundle.location.country.service;

import com.example.travelling.bundle.location.city.data.CityData;
import com.example.travelling.bundle.location.city.service.CityService;
import com.example.travelling.bundle.location.country.data.CountryData;
import com.example.travelling.bundle.location.country.data.CountryJpaRepository;
import com.example.travelling.bundle.location.country.data.LocationData;
import com.example.travelling.bundle.location.country.domain.Country;
import com.example.travelling.bundle.location.province.Province;
import com.example.travelling.bundle.location.province.data.ProvinceData;
import com.example.travelling.bundle.location.province.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryJpaRepository repository;
    private final ProvinceService provinceService;
    private final CityService cityService;

    @Autowired
    public CountryServiceImpl(CountryJpaRepository repository, ProvinceService provinceService, CityService cityService) {
        this.repository = repository;
        this.provinceService = provinceService;
        this.cityService = cityService;
    }


    @Override
    public List<CountryData> findAll() {
        List<CountryData> countryDataList = new ArrayList<>();

        List<Country> all = repository.findAll();

        all.forEach(country -> {
            CountryData countryData = new CountryData(country);
            countryDataList.add(countryData);
        });
        return countryDataList;
    }

    @Override
    public CountryData findByCountryId(Long countryId) {
        Country country = repository.findById(countryId).orElseThrow();
        List<Province> provinceList = provinceService.findAllByCountryId(countryId);

        CountryData countryData = new CountryData(country,provinceList);
        return countryData;
    }

    @Override
    public LocationData findCityByCountryIdAndProvinceId(Long countryId, Long provinceId) {
        Country country = repository.findById(countryId).orElseThrow();
        CountryData countryData = new CountryData(country);
        ProvinceData provinceDataList = provinceService.findById(provinceId);
        List<CityData> cityDataList = cityService.findByProvinceId(provinceId);

        return new LocationData(countryData,provinceDataList,cityDataList);
    }
}
