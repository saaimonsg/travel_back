package com.example.travelling.bundle.location.domain;

import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.province.domain.Province;
import com.example.travelling.infra.core.domain.AbstractPersistableCustom;
import com.example.travelling.infra.core.domain.appuser.data.AppUserData;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "app_location")
public class Location extends AbstractPersistableCustom {
    @ManyToOne(fetch = FetchType.EAGER)
    private Country fromCountryId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Province fromProvinceId;
    @ManyToOne(fetch = FetchType.EAGER)
    private City fromCityId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Country toCountryId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Province toProvinceId;
    @ManyToOne(fetch = FetchType.EAGER)
    private City toCityId;
    private Date departureDate;
    private String departureTime;
    private Long passengers;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser appUser;

    public Location() {
        //
    }

    public Location(Country fromCountryId, Province fromProvinceId, City fromCityId, Country toCountryId,
                    Province toProvinceId, City toCityId, Date departureDate, String departureTime,
                    Long passengers, String description, AppUser appUser) {
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
        this.appUser = appUser;
    }


}