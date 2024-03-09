package com.example.travelling.bundle.location.data;

import com.example.travelling.bundle.location.domain.Location;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationJpaRepository extends JpaRepository<Location,Long> {

//    @Query("SELECT al FROM  Location al  WHERE al.id = ?1 and al.appUser.id = ?2")
    Location findLocationByIdAndAppUser_Id(Long locationId, Long userId);

    List<Location> findLocationsByAppUser_Id(Long id);
}
