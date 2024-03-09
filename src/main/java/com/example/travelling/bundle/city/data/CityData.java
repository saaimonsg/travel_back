package com.example.travelling.bundle.city.data;

import com.example.travelling.bundle.city.domain.City;

public class CityData {

    private Long id;
    private String name;
    private String iso;
    private Long provinceId;

    public CityData(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.iso = city.getIso();
        this.provinceId = city.getProvinceId();
    }
}
