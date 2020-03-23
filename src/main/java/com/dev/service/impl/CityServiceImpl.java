package com.dev.service.impl;

import com.dev.dao.CityDao;
import com.dev.model.City;
import com.dev.service.CityService;
import com.dev.util.GenerationRandomIdCityUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;
    private List<Long> listOfCityIdThatHaveAlreadyBeen;

    public CityServiceImpl() {
        listOfCityIdThatHaveAlreadyBeen = new ArrayList<>();
    }

    @Override
    public Optional<City> getCityByName(String name) throws SQLException {
        return cityDao.getCityByName(name);
    }

    @Override
    public Optional<City> getCityById(Long id) throws SQLException {
        return cityDao.getCityById(id);
    }

    @Override
    public boolean addCityIdToListCalledCities(Long idCity) {
        if (!isCityAlreadyBeenCalled(idCity)) {
            listOfCityIdThatHaveAlreadyBeen.add(idCity);
            return true;
        }
        return false;
    }

    @Override
    public Optional<City> getCityBesidesTheNamesAlreadyExist(String cityName) throws SQLException {
        return cityDao.getCityBesidesTheNamesAlreadyExist(listOfCityIdThatHaveAlreadyBeen,
                cityName.charAt(cityName.length() - 1));
    }

    @Override
    public boolean checkingTheNameOfCityThatUserEntered(String cityName) throws SQLException {
        if (isCityExistInDataBase(cityName) && checkingCityNameBeginWithTheLastLetter(cityName)) {
            City city = getCityByName(cityName).get();
            Long idCity = city.getIdCity();
            if (!isCityAlreadyBeenCalled(idCity)) {
                addCityIdToListCalledCities(idCity);
                return true;
            }
        }
        return false;
    }

    @Override
    public City getRandomCityFromBeginPlay() throws SQLException {
        long numbersCities = getNumbersCities();
        City city = getCityById(GenerationRandomIdCityUtil.RandomGenerator(1, numbersCities)).get();
        String cityName = city.getCityName();
        char lastLetter = cityName.charAt(cityName.length() - 1);
        while (lastLetter == 'ь' || lastLetter == 'й') {
            city = getCityById(GenerationRandomIdCityUtil.RandomGenerator(1, numbersCities)).get();
            cityName = city.getCityName();
            lastLetter = cityName.charAt(cityName.length() - 1);
        }
        return city;
    }

    private long getNumbersCities() throws SQLException {
        return cityDao.getNumbersCities();
    }

    private boolean isCityExistInDataBase(String cityName) throws SQLException {
        return getCityByName(cityName).isPresent();
    }

    private boolean isCityAlreadyBeenCalled(Long idCity) {
        return listOfCityIdThatHaveAlreadyBeen.contains(idCity);
    }

    private boolean checkingCityNameBeginWithTheLastLetter(String cityName) throws SQLException {
        City city = cityDao.getCityById(listOfCityIdThatHaveAlreadyBeen
                .get(listOfCityIdThatHaveAlreadyBeen.size() - 1)).get();
        String cityNameThatHaveAlreadyBeenCalled = city.getCityName().toLowerCase();
        char lastLetter = cityNameThatHaveAlreadyBeenCalled
                .charAt(cityNameThatHaveAlreadyBeenCalled.length() - 1);
        char firstLetter = Character.toLowerCase(cityName.charAt(0));
        return lastLetter == firstLetter;
    }
}
