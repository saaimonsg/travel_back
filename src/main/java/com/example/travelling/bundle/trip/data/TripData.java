package com.example.travelling.bundle.trip.data;

import com.example.travelling.bundle.city.domain.City;
import com.example.travelling.bundle.country.domain.Country;
import com.example.travelling.bundle.province.domain.Province;
import com.example.travelling.bundle.trip.domain.Trip;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@ToString
public class TripData {

    private final Long likes;
    private  Long id;
    private final Country fromCountry;
    private final Province fromProvince;
    private final City fromCity;
    private final Country toCountry;
    private final Province toProvince;
    private final City toCity;
    private final Date departureDate;
    private final String departureTime;
    private final long amountPassengers;
    private List<AppUser> passengers = new ArrayList<>();
    private Long emptyPlaces;
    private final String description;
    private final Long userId;
    private final String username;
    private final boolean onlyFriends;


    public TripData(Trip trip) {
        this.id = trip.getId();
        this.fromCountry = trip.getFromCountry();
        this.fromProvince = trip.getFromProvince();
        this.fromCity = trip.getFromCity();
        this.toCountry = trip.getToCountry();
        this.toProvince = trip.getToProvince();
        this.toCity = trip.getToCity();
        this.departureDate = trip.getDepartureDate();
        this.departureTime = trip.getDepartureTime();
        this.passengers = trip.getPassengers();
        this.description = trip.getDescription();
        this.userId = trip.getOwner().getId();
        this.username = trip.getOwner().getName();
        this.onlyFriends = trip.isOnlyFriends();
        this.likes = trip.getOwner().getLikes();
        this.amountPassengers = 0;
    }
}
