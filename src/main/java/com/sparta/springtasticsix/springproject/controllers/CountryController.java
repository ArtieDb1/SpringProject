package com.sparta.springtasticsix.springproject.controllers;

import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CountryController {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostMapping("/country/createCountry")
    public CountryDTO createCountry(@RequestBody CountryDTO newCountry) {
        countryRepository.save(newCountry);
        return newCountry;
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
    public CountryDTO updateCountry(@PathVariable String code, @RequestBody CountryDTO updateCountry) {
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
        return countryRepository.save(country);
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
