package com.example.travelling.bundle.location.city.service;

import com.example.travelling.bundle.location.city.data.CityData;
import com.example.travelling.bundle.location.city.data.CityJpaRepository;
import com.example.travelling.bundle.location.city.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityJpaRepository repository;

    @Override
    public List<CityData> findByProvinceId(Long provinceId) {
        List<City> cityList = repository.findAllByProvinceId(provinceId);
        List<CityData> cityDataList = new ArrayList<>();
        cityList.forEach(city -> {
            cityDataList.add(new CityData(city));
        });
        return cityDataList;
    }
}
