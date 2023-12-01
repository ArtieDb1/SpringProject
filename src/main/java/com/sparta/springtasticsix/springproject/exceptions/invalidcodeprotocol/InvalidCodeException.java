package com.sparta.springtasticsix.springproject.exceptions.invalidcodeprotocol;


public class InvalidCodeException extends Exception {
    public InvalidCodeException(Integer id) {

        super("Country with ID: " + id + " doesnt exist :(");

    }
}
