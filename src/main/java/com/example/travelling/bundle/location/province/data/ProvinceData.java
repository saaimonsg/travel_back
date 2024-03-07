package com.example.travelling.bundle.location.province.data;

import com.example.travelling.bundle.location.city.data.CityData;
import com.example.travelling.bundle.location.province.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceData {
    private Long id;
    private String name;
    private String iso;
    private Long countryId;
    private List<CityData> cityDataList;

    public ProvinceData(Province province) {
        this.id = province.getId();
        this.name = province.getName();
        this.iso = province.getIso();
        this.countryId = province.getCountryId();
        this.cityDataList = null;
    }
}
