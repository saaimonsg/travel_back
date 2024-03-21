package com.example.travelling.bundle.country.data;

import com.example.travelling.bundle.country.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryJpaRepository extends JpaRepository<Country, Long> {

    @Query(value = "select c from Country c order by c.name")
    List<Country> orderAllByName();

    @Query(value= "select c from Country c where c.name like :pattern% ")
    List<Country> searchByName(@Param("pattern") String pattern);
}
