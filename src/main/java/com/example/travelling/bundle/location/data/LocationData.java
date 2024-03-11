package com.example.travelling.bundle.location.data;

import com.example.travelling.bundle.location.domain.Location;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class LocationData {

    private final  Long id;
    private final  Long fromCountryId;
    private final  Long fromProvinceId;
    private final  Long fromCityId;
    private final  Long toCountryId;
    private final  Long toProvinceId;
    private final  Long toCityId;
    private final  Date departureDate;
    private final  String departureTime;
    private final  Long passengers;
    private final  String description;
    private final  Long userId;
    private final  String username;


    public LocationData(Location loc, AppUser appUser) {
        this.id = loc.getId();
        this.fromCountryId = loc.getFromCountryId().getId();
        this.fromProvinceId = loc.getFromProvinceId().getId();
        this.fromCityId = loc.getFromCityId().getId();
        this.toCountryId = loc.getToCountryId().getId();
        this.toProvinceId = loc.getToProvinceId().getId();
        this.toCityId = loc.getToCityId().getId();
        this.departureDate = loc.getDepartureDate();
        this.departureTime = loc.getDepartureTime();
        this.passengers = loc.getPassengers();
        this.description = loc.getDescription();
        this.userId = appUser.getId();
        this.username = appUser.getName();
    }
}
