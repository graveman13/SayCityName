package com.dev.dto;

public class CorrectlyMessageDtoImp implements MessageDto<CorrectlyMessageDtoImp> {
    private String message;
    private String cityName;

    public CorrectlyMessageDtoImp(String message, CityMessageDtoImp cityDto) {
        this.message = message;
        this.cityName = cityDto.getMessage();
    }

    @Override
    public String getMessage() {
        return message + cityName;
    }
}
