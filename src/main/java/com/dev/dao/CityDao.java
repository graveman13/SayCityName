package com.dev.dao;

import com.dev.model.City;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CityDao {
    Optional<City> getCityByName(String name) throws SQLException;

    Optional<City> getCityById(Long id) throws SQLException;

    Optional<City> getCityBesidesTheNamesAlreadyExist(List<Long> listId, char lastLetter) throws SQLException;

    long getNumbersCities() throws SQLException;
}
