package com.dev.dto;

public class EndGameMessageDtoImpl implements MessageDto{
    private String message;

    public EndGameMessageDtoImpl() {
        this.message = "Дякую за гру!";
    }
    @Override
    public String getMessage() {
        return message;
    }
}
