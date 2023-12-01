package com.sparta.springtasticsix.springproject.exceptions.duplicatecountryprotocol;

public class DuplicateCountryException extends Exception {
    public DuplicateCountryException(String code) {
        super("Country with code: " + code + " already exists.");
    }
}
