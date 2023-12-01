package com.sparta.springtasticsix.springproject.exceptions;



public class CustomResponse {

    private String message;
    private int statusCode;

    public CustomResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage(){
        return message;
    }

    public int getStatus(){
        return statusCode;
    }
}
