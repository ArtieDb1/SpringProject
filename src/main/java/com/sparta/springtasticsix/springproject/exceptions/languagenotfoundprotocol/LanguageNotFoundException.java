package com.sparta.springtasticsix.springproject.exceptions.languagenotfoundprotocol;



public class LanguageNotFoundException extends Exception {
    public LanguageNotFoundException(String language) {
            super(language + " has not been found in the database.");
    }
}
