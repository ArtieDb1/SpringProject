package com.sparta.springtasticsix.springproject.exceptions.citynotfoundprotocol;

import com.sparta.springtasticsix.springproject.model.entities.CityDTO;

public class CityNotFoundException extends Exception {


    public CityNotFoundException(Integer id) {
        super("Couldn't find City with id: " + id);
    }
}
