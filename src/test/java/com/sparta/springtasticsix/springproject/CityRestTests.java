package com.sparta.springtasticsix.springproject;

import com.sparta.springtasticsix.springproject.controllers.CityController;
import com.sparta.springtasticsix.springproject.controllers.CountryController;
import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CityRestTests {

    private WebTestClient testClient;

    @Autowired
    private CityController cityController;
    @Autowired
    private CityRepository cityRepository;


    @BeforeEach
    void setUp() {
        testClient = WebTestClient
                .bindToController(cityController)
                .build();
    }

    @Test
    @DisplayName("Check given city code 11 returns Groningen")
    void checkGivenCityCode11ReturnsGroningen() {
        testClient
                .get()
                .uri("city/getById/AGO")
                .exchange()
                .expectBody(CityDTO.class)
                .value(country -> assertEquals("Groningen", cityRepository.findById(11).get().getName()));
    }

    @Test
    @DisplayName("Check 5 least populated districts")
    void check5LeastPopulatedDistricts() {
        testClient
                .get()
                .uri("city/getById/AGO")
                .exchange()
                .expectBody(CityDTO.class)
                .value(country -> assertEquals("West Island: 167, Fakaofo: 300, Home Island: 503, Wallis: 1137, Länsimaa: 1438", cityController.get5LeastPopulatedDistricts().toString()));
    }


}
