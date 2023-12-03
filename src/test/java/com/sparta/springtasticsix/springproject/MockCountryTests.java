package com.sparta.springtasticsix.springproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springtasticsix.springproject.controllers.CountryController;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CityRepository;
import com.sparta.springtasticsix.springproject.model.repositories.CountryRepository;
//import com.sparta.springtasticsix.springproject.security.AuthenticationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryController.class)
@AutoConfigureMockMvc
public class MockCountryTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private CityRepository cityRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @DisplayName("Check create country returns given country")
    void checkCreateCountryReturnsGivenCountry() throws Exception {
        CountryDTO mockCountry = new CountryDTO();
        mockCountry.setCode("XYZ");
        Mockito.when(countryRepository.save(mockCountry)).thenReturn(mockCountry);

        mockMvc
                .perform(post("http://localhost:8080/country/createCountry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockCountry)))
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

    @Test
    @DisplayName("Check update country given AGO with new name returns Agola")
    void checkUpdateCountryGivenAGOWithNewNameReturnsAgola() throws Exception {
        CountryDTO mockCountry = new CountryDTO();
        mockCountry.setCode("AGO");
        mockCountry.setName("Agola");
        mockCountry.setContinent("Africa");
        mockCountry.setRegion("Central Africa");
        mockCountry.setSurfaceArea(BigDecimal.valueOf(1246700));
        mockCountry.setIndepYear((short) 1975);
        mockCountry.setPopulation(12878000);
        mockCountry.setLifeExpectancy(BigDecimal.valueOf(38.3));
        mockCountry.setGnp(BigDecimal.valueOf(6648.00));
        mockCountry.setGNPOld(BigDecimal.valueOf(7984.00));
        mockCountry.setLocalName("Angola");
        mockCountry.setGovernmentForm("Republic");
        mockCountry.setHeadOfState("José Eduardo dos Santos");
        mockCountry.setCapital(56);
        mockCountry.setCode2("AO");

        Mockito.when(countryRepository.findById(mockCountry.getCode())).thenReturn(Optional.of(mockCountry));

        mockMvc
                .perform(patch("http://localhost:8080/country/updateCountry/AGO")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockCountry)))
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

}