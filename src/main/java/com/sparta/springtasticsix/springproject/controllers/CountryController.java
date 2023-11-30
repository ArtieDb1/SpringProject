package com.sparta.springtasticsix.springproject.controllers;

import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

//    private final AuthorRepository authorRepository;
//
//    @Autowired
//    public AuthorController(AuthorRepository authorRepository) {
//        this.authorRepository = authorRepository;
//    }

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostMapping("/country/createCountry")
    public Optional<CountryDTO> createCountry(@RequestBody CountryDTO newCountry) {
        countryRepository.save(newCountry);
        Optional<CountryDTO> newCountryOptional = Optional.of(newCountry);
        return newCountryOptional;
    }

    @GetMapping("/country/getByCode")
    public Optional<CountryDTO> getByCode(@RequestParam(name= "code", required = true) String code) {
        Optional<CountryDTO> checkCountry = countryRepository.findById(code);
        if(checkCountry.isPresent()) {
            return checkCountry;
        } else {
            return Optional.empty();
        }
    }

    @PatchMapping("/country/updateCountry/{code}")
    public Optional<CountryDTO> updateCountry(@PathVariable String code, @RequestBody CountryDTO updateCountry) {
        CountryDTO country = null;
        if(countryRepository.findById(code).isPresent()) {
            country = countryRepository.findById(code).get();
            country.setCode((updateCountry.getCode()));
            country.setName((updateCountry.getName()));
            country.setContinent((updateCountry.getContinent()));
            country.setRegion((updateCountry.getRegion()));
            country.setSurfaceArea((updateCountry.getSurfaceArea()));
            country.setIndepYear((updateCountry.getIndepYear()));
            country.setPopulation((updateCountry.getPopulation()));
            country.setLifeExpectancy((updateCountry.getLifeExpectancy()));
            country.setGnp((updateCountry.getGnp()));
            country.setGNPOld((updateCountry.getGNPOld()));
            country.setLocalName((updateCountry.getLocalName()));
            country.setGovernmentForm((updateCountry.getGovernmentForm()));
            country.setHeadOfState((updateCountry.getHeadOfState()));
            country.setCapital((updateCountry.getCapital()));
            country.setCode2((updateCountry.getCode2()));
        }
        countryRepository.save(country);
        return Optional.of(country);
    }

    @DeleteMapping("/country/deleteCountry/{code}")
    public String deleteCountry(@PathVariable String code) {
        Optional<CountryDTO> checkCountry = countryRepository.findById(code);
        if(checkCountry.isPresent()) {
            countryRepository.delete(checkCountry.get());
            return "Deleted: " + checkCountry.get().toString();
        } else {
            return "Country not found";
        }
    }
}
