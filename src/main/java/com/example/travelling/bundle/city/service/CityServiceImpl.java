package com.example.travelling.bundle.city.service;

import com.example.travelling.bundle.city.data.CityJpaRepository;
import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.province.data.ProvinceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityJpaRepository repository;
    private final ProvinceJpaRepository provinceJpaRepository;
    @Override
    public List<City> findAllByProvinceId(Long provinceId) {
        provinceJpaRepository.findById(provinceId).orElseThrow();
        return repository.findAllByProvinceId(provinceId);
    }

    @Override
    public City findById(Long cityID) {
        return repository.findById(cityID).orElseThrow();
    }
}
