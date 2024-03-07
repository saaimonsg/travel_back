package com.example.travelling.bundle.location.province.service;

import com.example.travelling.bundle.location.province.Province;
import com.example.travelling.bundle.location.province.data.ProvinceData;
import com.example.travelling.bundle.location.province.data.ProvinceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceJpaRepository repository;

     @Autowired
    public ProvinceServiceImpl(ProvinceJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Province> findAllByCountryId(Long countryId) {
        return repository.findAllByCountryId(countryId);
    }

    @Override
    public ProvinceData findById(Long provinceId) {
        Province province = repository.findById(provinceId).orElseThrow();
        return new ProvinceData(province);
    }
}
