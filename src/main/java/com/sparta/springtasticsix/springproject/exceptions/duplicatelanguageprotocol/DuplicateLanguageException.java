package com.sparta.springtasticsix.springproject.exceptions.duplicatelanguageprotocol;

import com.sparta.springtasticsix.springproject.model.entities.CountryLanguageDTO;

public class DuplicateLanguageException extends Exception {
    public DuplicateLanguageException(CountryLanguageDTO newLanguage) {
        super(newLanguage.getId().getLanguage() +" already exists in database.");
    }
}
