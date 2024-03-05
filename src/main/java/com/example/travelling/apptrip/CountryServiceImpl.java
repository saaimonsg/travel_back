package com.example.travelling.apptrip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryJpaRepository repository;

    @Autowired
    public CountryServiceImpl(CountryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CountryData> findAll() {
        List<CountryData> countryDataList = new ArrayList<>();

        List<Country> all = repository.findAll();

        all.forEach(country -> {

            CountryData countryData = new CountryData(country);

//            long countryId = country.getId();
//            provinceService.findAllByCountryId(countryId).forEach(province -> {
//                if (province.getCountryId().equals(countryId)) {
//                    countryData.getProvinceList().add(province);
//                }
//            });

            countryDataList.add(countryData);
        });
        return countryDataList;
    }
}
