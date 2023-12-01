package com.sparta.springtasticsix.springproject.exceptions.duplicatelanguageprotocol;

import com.sparta.springtasticsix.springproject.model.entities.CountrylanguageDTO;

public class DuplicateLanguageException extends Exception {
    public DuplicateLanguageException(CountrylanguageDTO newLanguage) {
        super(newLanguage +" already exists in database");
    }
}
