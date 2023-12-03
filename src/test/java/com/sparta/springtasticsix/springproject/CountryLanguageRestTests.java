package com.sparta.springtasticsix.springproject;

import com.sparta.springtasticsix.springproject.controllers.CityController;
import com.sparta.springtasticsix.springproject.controllers.CountryLanguageController;
import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CityRepository;
import com.sparta.springtasticsix.springproject.model.repositories.CountryLanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CountryLanguageRestTests {

    private WebTestClient testClient;

    @Autowired
    private CountryLanguageController countryLanguageController;
    @Autowired
    private CountryLanguageRepository countryLanguageRepository;


    @BeforeEach
    void setUp() {
        testClient = WebTestClient
                .bindToController(countryLanguageController)
                .build();
    }

//    @Test
//    @DisplayName("Check given country code PRT returns Portuguese")
//    void checkGivenCountryCodePRTReturnsPortuguese() {
//        testClient
//                .get()
//                .uri("city/getByCode?code=PRT")
//                .exchange()
//                .expectBody(CityDTO.class)
//                .value(country -> assertEquals("Portuguese", countryLanguageController.getByCode("PRT", "Portuguese")));
//    }
}
