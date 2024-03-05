package com.example.travelling.apptrip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CountryData {

    private Long id;
    private String iso;
    private String name;
    private String nice_name;
    private String iso3;
    private Integer num_code;
    private Integer phone_code;

    public CountryData(Country country) {
        this.id = country.getId();
        this.iso = country.getIso();
        this.name = country.getName();
        this.nice_name = country.getNiceName();
        this.iso3 = country.getIso3();
        this.num_code = country.getNumCode();
        this.phone_code = country.getPhoneCode();
    }
}
