package com.example.travelling.bundle.province.data;

import com.example.travelling.bundle.province.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceJpaRepository extends JpaRepository<Province,Long> {
    List<Province> findAllByCountryId(Long countryId);
}
