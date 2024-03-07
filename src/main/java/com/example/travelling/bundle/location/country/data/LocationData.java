package com.example.travelling.bundle.location.country.data;

import com.example.travelling.bundle.location.city.data.CityData;
import com.example.travelling.bundle.location.province.data.ProvinceData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationData {
    private CountryData country;
    private List<CountryData> countryDataList;
    private ProvinceData province;
    private List<ProvinceData> provinceDataList;
    private CityData city;
    private List<CityData> cityList;

    public LocationData(CountryData country, ProvinceData province, List<CityData> cityList) {
        this.country = country;
        this.province = province;
        this.city = null;
        this.countryDataList = null;
        this.provinceDataList = null;
        this.cityList = cityList;
    }
}
