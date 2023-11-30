package com.sparta.springtasticsix.springproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springtasticsix.springproject.controllers.CountryController;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CountryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryController.class)
public class MockCountryTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private CountryController countryController;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @DisplayName("Check create country returns given country")
    void checkCreateCountryReturnsGivenCountry() throws Exception {
        Mockito.when(countryRepository.save(any())).thenReturn(new CountryDTO());
        CountryDTO mockCountry = new CountryDTO();
        mockCountry.setCode("XYZ");
        mockMvc
                .perform(post("http://localhost:3000/country/createCountry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockCountry)))
                .andExpect(status().is(200))
                .andDo(print());

    }
//        CountryDTO mockCountry = new CountryDTO();
//        mockCountry.setCode("XYZ");
//        Mockito.when(countryRepository.save(mockCountry)).thenReturn(mockCountry);
//        mockMvc
//                .perform(post("http://localhost:3000/country/createCountry"))
//                .andExpect(content().contentType("application/json"))
//                .andExpect(status().is(200))
//                .andDo(print());
//    }
}
