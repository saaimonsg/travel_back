package com.example.travelling.bundle.trip.data;

import com.example.travelling.bundle.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripJpaRepository extends JpaRepository<Trip,Long> {

//    @Query("SELECT al FROM  Location al  WHERE al.id = ?1 and al.appUser.id = ?2")
    Trip findLocationByIdAndOwner_Id(Long locationId, Long userId);

    List<Trip> findAllByOwner_Id(Long userId);

}
