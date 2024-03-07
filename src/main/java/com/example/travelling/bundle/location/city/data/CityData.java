package com.example.travelling.bundle.location.city.data;

import com.example.travelling.bundle.location.city.domain.City;

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
