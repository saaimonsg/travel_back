package com.example.travelling.bundle.country.data;

import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.province.domain.Province;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CountryData {

    private Long id;
    private String iso;
    private String name;
    private String nice_name;
    private String iso3;
    private Integer num_code;
    private Integer phone_code;

    private List<Province> provinceList;


    public CountryData(Country country, List<Province> provinceList) {
        this.id = country.getId();
        this.iso = country.getIso();
        this.name = country.getName();
        this.nice_name = country.getNiceName();
        this.iso3 = country.getIso3();
        this.num_code = country.getNumCode();
        this.phone_code = country.getPhoneCode();
        this.provinceList = provinceList;
    }

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
