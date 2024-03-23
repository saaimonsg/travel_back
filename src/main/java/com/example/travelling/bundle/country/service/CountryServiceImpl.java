package com.example.travelling.bundle.country.service;

import com.example.travelling.bundle.country.data.CountryJpaRepository;
import com.example.travelling.bundle.country.domain.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryJpaRepository repository;


    @Override
    public List<Country> findAll() {
        return repository.orderAllByName();
    }

    @Override
    public List<Country> search(String pattern) {

        return repository.searchByName(pattern.toUpperCase());
    }

    @Override
    public Country findById(Long countryId) {
        return repository.findById(countryId).orElseThrow();
    }

    @Override
    public Country save(Country country) {
        if (country.getIso() == null || country.getIso().isEmpty()) {
            throw new IllegalArgumentException("Country ISO is required");
        } else if (country.getName() == null || country.getName().isEmpty()) {
            throw new IllegalArgumentException("Country name is required");
        } else if (country.getNiceName() == null || country.getNiceName().isEmpty()) {
            throw new IllegalArgumentException("Country name is required");
        }
        return repository.save(country);
    }

    @Override
    public Country delete(Long countryId) {
        return repository.findById(countryId).map(country -> {
            repository.delete(country);
            return country;
        }).orElseThrow();
    }

    @Override
    public Country update(Country country) {
        Country data = repository.findById(country.getId()).orElseThrow();
        if (!data.getNiceName().equals(country.getNiceName())) {
            data.setNiceName(country.getNiceName());
        }
        if (!data.getName().equals(country.getName())) {
            data.setName(country.getName());
        }
        if (!data.getIso().equals(country.getIso())) {
            data.setIso(country.getIso());
        }
        if (!data.getIso3().equals(country.getIso3())) {
            data.setNiceName(country.getNiceName());
        }
        if (!data.getNumCode().equals(country.getNumCode())) {
            data.setNumCode(country.getNumCode());
        }
        if (!data.getPhoneCode().equals(country.getPhoneCode())) {
            data.setPhoneCode(country.getPhoneCode());
        }

        return repository.save(data);
    }
}
