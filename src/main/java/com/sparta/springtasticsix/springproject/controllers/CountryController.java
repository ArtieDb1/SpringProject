package com.sparta.springtasticsix.springproject.controllers;

import com.sparta.springtasticsix.springproject.exceptions.countrynotfoundprotocol.CountryNotFoundException;
import com.sparta.springtasticsix.springproject.exceptions.duplicatecountryprotocol.DuplicateCountryException;
import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CityRepository;
import com.sparta.springtasticsix.springproject.model.repositories.CountryRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }
    @Operation(summary = "Create a country by supplying it a body in JSON format")
    @PostMapping("/country/createCountry")
    public Optional<CountryDTO> createCountry(@RequestBody CountryDTO newCountry) throws DuplicateCountryException {
        if (countryRepository.existsById(newCountry.getCode())) {
            throw new DuplicateCountryException(newCountry.getCode());
        }
        countryRepository.save(newCountry);
        return Optional.of(newCountry);
    }

    @Operation(summary = "Get a country by its country code")
    @GetMapping("/country/getByCode")
    public Optional<CountryDTO> getByCode(@RequestParam(name= "code", required = true) String code) throws CountryNotFoundException {
        Optional<CountryDTO> checkCountry = countryRepository.findById(code);
        if(checkCountry.isPresent()) {
            return checkCountry;
        } else {
            throw new CountryNotFoundException(code);
        }
    }
    @Operation(summary = "Update a country by its country code, supply body in JSON format")
    @PatchMapping("/country/updateCountry/{code}")
    public Optional<CountryDTO> updateCountry(@PathVariable String code, @RequestBody CountryDTO updateCountry) throws CountryNotFoundException {
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
        }else{
            throw new CountryNotFoundException(code);
        }
        countryRepository.save(country);
        return Optional.of(country);
    }


    @Operation(summary = "Delete a country by its country code")
    @DeleteMapping("/country/deleteCountry/{code}")
    public String deleteCountry(@PathVariable String code) throws CountryNotFoundException {
        Optional<CountryDTO> checkCountry = countryRepository.findById(code);
        if(checkCountry.isPresent()) {
            countryRepository.delete(checkCountry.get());
            return "Deleted: " + checkCountry.get();
        } else {
            throw new CountryNotFoundException(code);
        }
    }
    @Operation(summary = "Get all countries with no Head of State")
    @GetMapping("/country/getCountriesWithNoHeadOfState")
    public List<CountryDTO> getCountriesWithNoHeadOfState() {
        List<CountryDTO> countries = countryRepository.findCountriesWithNullOrBlankHeadOfState();
        return countries;
    }
    @Operation(summary = "Get percentage of population of a country by its country code")
    @GetMapping("/country/getPercentageOfPopulation/{code}")
    public Double getPercentageOfPopulation(@PathVariable String code) throws CountryNotFoundException {
        Optional<CountryDTO> country = countryRepository.findById(code);
        if(country.isPresent()) {
            List<CityDTO> cities = cityRepository.findCityDTOByCountryCode(country.get());
            Integer largestPOP = cities.get(0).getPopulation();
            for(CityDTO city: cities) {
                if(city.getPopulation() > largestPOP) {
                    largestPOP = city.getPopulation();
                }
            }
            Integer countryPOP = country.get().getPopulation();
            return ((double) largestPOP / countryPOP) * 100;
        } else {
            throw new CountryNotFoundException(code);
        }

    }

    @Operation(summary = "Get the country with the most cities in the database")
    @GetMapping("/country/getMostCities")
    public String getMostCities() {
        List<CityDTO> cities = cityRepository.findByOrderByCountryCode();
        int largestCount = 0;
        int currentCount = 1;
        CountryDTO largestCode = cities.get(0).getCountryCode();
        for(int i = 0; i < cities.size(); i++) {
            if(i+1 < cities.size() && cities.get(i+1).getCountryCode().equals(cities.get(i).getCountryCode())) {
                currentCount++;
            } else {
                if(currentCount > largestCount) {
                    largestCount = currentCount;
                    largestCode = cities.get(i).getCountryCode();
                }
                currentCount = 1;
            }
        }
        return "Country: " + largestCode.getName() + " has " + largestCount + " cities";
    }
}
