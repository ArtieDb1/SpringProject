package com.sparta.springtasticsix.springproject.exceptions.citynotfoundprotocol;

public class CityNotFoundException extends Exception {


    public CityNotFoundException(Integer id) {
        super("Couldn't find City with id: " + id);
    }
}
