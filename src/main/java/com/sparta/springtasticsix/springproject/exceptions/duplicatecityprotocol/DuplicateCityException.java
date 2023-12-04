package com.sparta.springtasticsix.springproject.exceptions.duplicatecityprotocol;

import com.sparta.springtasticsix.springproject.model.entities.CityDTO;

public class DuplicateCityException extends Exception {
    public DuplicateCityException(CityDTO newCity) {
        super(newCity.getName() + " already exists in database.");
    }
}
