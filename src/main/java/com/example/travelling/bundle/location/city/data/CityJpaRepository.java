package com.example.travelling.bundle.location.city.data;

import com.example.travelling.bundle.location.city.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityJpaRepository extends JpaRepository<City, Long> {
    List<City> findAllByProvinceId(Long provinceId);
}
