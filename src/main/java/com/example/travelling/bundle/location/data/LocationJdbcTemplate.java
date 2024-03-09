package com.example.travelling.bundle.location.data;

import com.example.travelling.bundle.location.domain.Location;
import com.example.travelling.infra.core.domain.appuser.domain.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LocationJdbcTemplate {
    private final DataSource dataSource ;
    private final JdbcTemplate jdbcTemplate;

    public List<Location> findLocationsByAppUserId(AppUser appUser) {
        String query = "SELECT * FROM app_location WHERE app_user_id = "+appUser.getId();
        return  jdbcTemplate.query(query, new LocationRowMapper());
    }
}
