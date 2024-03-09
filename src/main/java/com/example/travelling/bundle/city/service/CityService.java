package com.example.travelling.bundle.city.service;

import com.example.travelling.bundle.city.domain.City;

import java.util.List;

public interface CityService {

    List<City> findAllByProvinceId(Long provinceId);

    City findById(Long toCityId);
}
