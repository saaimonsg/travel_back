package com.example.travelling.bundle.location.city.service;

import com.example.travelling.bundle.location.city.data.CityData;

import java.util.List;

public interface CityService {

    List<CityData> findByProvinceId(Long provinceId);
}
