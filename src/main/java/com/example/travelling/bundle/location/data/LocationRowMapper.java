package com.example.travelling.bundle.location.data;

import com.example.travelling.bundle.location.domain.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRowMapper implements RowMapper<Location>
{
    @Override
    public Location mapRow(ResultSet rs, int i) throws SQLException {
        LocationData locationData = new LocationData();
        locationData.setId(rs.getLong("id"));
        locationData.setFromCountryId(rs.getLong("from_country_id"));
        locationData.setFromProvinceId(rs.getLong("from_province_id"));
        locationData.setFromCityId(rs.getLong("from_city_id"));
        locationData.setToCountryId(rs.getLong("to_country_id"));
        locationData.setToProvinceId(rs.getLong("to_province_id"));
        locationData.setToCityId(rs.getLong("to_city_id"));
        locationData.setDepartureDate(rs.getDate("departure_date"));
        locationData.setDepartureTime(rs.getString("departure_time"));
        locationData.setPassengers(rs.getLong("passengers"));
        locationData.setDescription(rs.getString("description"));
        locationData.setUserId(rs.getLong("app_user_id"));
        return null;
    }
}
