package com.example.travelling.bundle.location.province.service;


import com.example.travelling.bundle.location.province.Province;
import com.example.travelling.bundle.location.province.data.ProvinceData;

import java.util.List;

public interface ProvinceService {


    List<Province> findAllByCountryId(Long countryID);

    ProvinceData findById(Long provinceId);
}
