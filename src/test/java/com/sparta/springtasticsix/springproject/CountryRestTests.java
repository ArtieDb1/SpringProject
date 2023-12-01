package com.sparta.springtasticsix.springproject;

import com.sparta.springtasticsix.springproject.controllers.CountryController;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CountryRestTests {

    private WebTestClient testClient;

    @Autowired
    private CountryController countryController;

    @BeforeEach
    void setUp() {
        testClient = WebTestClient
                .bindToController(countryController)
                .build();
    }

    @Test
    @DisplayName("Check given country code AGO returns Angola")
    void checkGivenCountryCodeAGOReturnsAngola() {
        testClient
                .get()
                .uri("http://localhost:3000/country/getByCode?code=AGO")
                .exchange()
                .expectBody(CountryDTO.class)
                .value(country -> assertEquals("Angola", country.getName()));
    }


}
