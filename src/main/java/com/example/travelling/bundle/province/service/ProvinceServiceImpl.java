package com.example.travelling.bundle.province.service;

import com.example.travelling.bundle.country.data.CountryJpaRepository;
import com.example.travelling.bundle.province.data.ProvinceJpaRepository;
import com.example.travelling.bundle.province.domain.Province;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceJpaRepository repository;
    private final CountryJpaRepository countryJpaRepository;

    @Override
    public List<Province> findAllByCountryId(Long countryId) {
        countryJpaRepository.findById(countryId).orElseThrow();
        return repository.findAllByCountryId(countryId);
    }

    @Override
    public Province findById(Long provinceId) {
        return repository.findById(provinceId).orElseThrow();
    }
}
