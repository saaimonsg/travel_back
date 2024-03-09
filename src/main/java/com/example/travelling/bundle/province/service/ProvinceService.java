package com.example.travelling.bundle.province.service;


import com.example.travelling.bundle.province.domain.Province;

import java.util.List;

public interface ProvinceService {


    List<Province> findAllByCountryId(Long countryId);

    Province findById(Long toProvinceId);
}
