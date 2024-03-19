package com.example.travelling.bundle.trip.domain;

import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.province.domain.Province;
import com.example.travelling.infra.core.jpa.AbstractPersistableCustom;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "app_trip")
public class Trip extends AbstractPersistableCustom {
    @ManyToOne(fetch = FetchType.EAGER)
    private Country fromCountry;
    @ManyToOne(fetch = FetchType.EAGER)
    private Province fromProvince;
    @ManyToOne(fetch = FetchType.EAGER)
    private City fromCity;
    @ManyToOne(fetch = FetchType.EAGER)
    private Country toCountry;
    @ManyToOne(fetch = FetchType.EAGER)
    private Province toProvince;
    @ManyToOne(fetch = FetchType.EAGER)
    private City toCity;
    private Date departureDate;
    private String departureTime;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser owner;
    private Long amountPassengers;
    @OneToMany(fetch = FetchType.EAGER)
    private List<AppUser> passengers;

    private boolean isOnlyFriends;

    public Trip() {
        //
    }

    public Trip(Country fromCountry, Province fromProvince, City fromCity, Country toCountry, Province toProvince, City toCity, Date departureDate, String departureTime, String description, AppUser appUser, List<AppUser> passengers, Long amountPassengers) {
        this.fromCountry = fromCountry;
        this.fromProvince = fromProvince;
        this.fromCity = fromCity;
        this.toCountry = toCountry;
        this.toProvince = toProvince;
        this.toCity = toCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.description = description;
        this.owner = appUser;
        this.passengers = passengers;
        this.amountPassengers = amountPassengers;
    }


    public Trip(Country fromCountry, Province fromProvince, City fromCity, Country toCountry,
                Province toProvince, City toCity, Date departureDate, String departureTime,
                Long amountPassengers, String description, AppUser appUser, Boolean isOnlyFriends) {
        this.fromCountry = fromCountry;
        this.fromProvince = fromProvince;
        this.fromCity = fromCity;
        this.toCountry = toCountry;
        this.toProvince = toProvince;
        this.toCity = toCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.amountPassengers = amountPassengers;
        this.description = description;
        this.owner = appUser;
        this.isOnlyFriends = isOnlyFriends;
    }


}