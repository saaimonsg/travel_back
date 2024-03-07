package com.example.travelling.bundle.location.country.data;

import com.example.travelling.bundle.location.country.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryJpaRepository extends JpaRepository<Country,Long> {
}
