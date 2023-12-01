package com.sparta.springtasticsix.springproject.exceptions.countrynotfoundprotocol;

public class CountryNotFoundException extends Exception {
    public CountryNotFoundException(String code) {
        super("Couldn't find country with code: " + code);
    }
}
