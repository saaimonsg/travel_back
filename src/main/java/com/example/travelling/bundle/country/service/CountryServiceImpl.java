package com.example.travelling.bundle.country.service;

import com.example.travelling.bundle.country.data.CountryJpaRepository;
import com.example.travelling.bundle.country.domain.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryJpaRepository repository;

    @Override
    public List<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Country findById(Long countryId) {
        return repository.findById(countryId).orElseThrow();
    }
}