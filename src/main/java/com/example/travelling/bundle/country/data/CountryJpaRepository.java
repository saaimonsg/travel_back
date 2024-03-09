package com.example.travelling.bundle.country.data;

import com.example.travelling.bundle.country.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryJpaRepository extends JpaRepository<Country,Long> {
}
