package com.dev.controllers;

import com.dev.dto.CityMessageDtoImp;
import com.dev.dto.EndGameMessageDtoImpl;
import com.dev.dto.MessageDto;
import com.dev.dto.CorrectlyMessageDtoImp;
import com.dev.model.City;
import com.dev.service.CityService;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    @Autowired
    CityService cityService;

    @GetMapping(value = "/begin", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public CityMessageDtoImp beginGame() throws SQLException {
        City city = cityService.getRandomCityFromBeginPlay();
        cityService.addCityIdToListCalledCities(city.getIdCity());
        return convertCityToCityDto(city);
    }

    @GetMapping(value = "/next")
    public MessageDto nextWord(@RequestParam(name = "word") CityMessageDtoImp cityDto) throws SQLException {
        String name = cityDto.getMessage();
        if (cityService.checkingTheNameOfCityThatUserEntered(name)) {
            Optional<City> city = cityService.getCityBesidesTheNamesAlreadyExist(name);
            if (city.isPresent()) {
                cityService.addCityIdToListCalledCities(city.get().getIdCity());
                return convertCityToCityDto(city.get());
            } else {
                return new CorrectlyMessageDtoImp("Ви перемогли", cityDto);
            }
        } else {
            return new CorrectlyMessageDtoImp("Некорректно введено назва міста ", cityDto);
        }
    }

    @PostMapping(value = "/end")
    public EndGameMessageDtoImpl endingGame(EndGameMessageDtoImpl endGameMessageDto) {
        return new EndGameMessageDtoImpl();
    }

    private CityMessageDtoImp convertCityToCityDto(City city) {
        return new CityMessageDtoImp(city.getCityName());
    }
}

