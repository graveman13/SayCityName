package com.dev.service;

import com.dev.model.City;

import java.sql.SQLException;
import java.util.Optional;

public interface CityService {

    Optional<City> getCityByName(String name) throws SQLException;

    Optional<City> getCityById(Long id) throws SQLException;

    boolean addCityIdToListCalledCities(Long idCity);

    Optional<City> getCityBesidesTheNamesAlreadyExist(String cityName) throws SQLException;

    boolean checkingTheNameOfCityThatUserEntered(String cityName) throws SQLException;

    City getRandomCityFromBeginPlay() throws SQLException;
}
