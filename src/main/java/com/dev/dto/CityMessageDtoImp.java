package com.dev.dto;

public class CityMessageDtoImp implements MessageDto<CityMessageDtoImp>{
    private String cityName;

    public CityMessageDtoImp(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String getMessage() {
        return cityName;
    }

}
