package com.example.travelling.bundle.location.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class LocationData {
    private Long id;
    private Long fromCountryId;
    private Long fromProvinceId;
    private Long fromCityId;
    private Long toCountryId;
    private Long toProvinceId;
    private Long toCityId;
    private Date departureDate;
    private String departureTime;
    private Long passengers;
    private String description;
    private Long userId;

    public LocationData(Long fromCountryId, Long fromProvinceId, Long fromCityId, Long toCountryId,
                        Long toProvinceId, Long toCityId, Date departureDate, String departureTime,
                        Long passengers, String description) {
        this.fromCountryId = fromCountryId;
        this.fromProvinceId = fromProvinceId;
        this.fromCityId = fromCityId;
        this.toCountryId = toCountryId;
        this.toProvinceId = toProvinceId;
        this.toCityId = toCityId;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.passengers = passengers;
        this.description = description;
    }

    public LocationData() {

    }
}
