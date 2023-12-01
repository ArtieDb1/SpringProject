package com.sparta.springtasticsix.springproject.controllers;


import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CityRepository;
import com.sparta.springtasticsix.springproject.model.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class CityController {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CityController(CityRepository cityRepository,
                          CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @PostMapping("/city/createCity")
    public CityDTO createCity(@RequestBody CityDTO newCity) {
        cityRepository.save(newCity);
        return newCity;
    }

    @GetMapping("/city/getById/{id}")
    public Optional<CityDTO> getByCode(@PathVariable Integer id) {
        Optional<CityDTO> checkCity = cityRepository.findById(id);
        if(checkCity.isPresent()) {
            return checkCity;
        } else {
            return Optional.empty();
        }
    }

    @PatchMapping("/city/updateCity/{id}")
    public CityDTO updateCountry(@PathVariable Integer id, @RequestBody CityDTO updatedCity) {
        CityDTO city = null;
        if(cityRepository.findById(id).isPresent()) {
            city = cityRepository.findById(id).get();
            city.setName(updatedCity.getName());
            city.setPopulation(updatedCity.getPopulation());
            city.setCountryCode(updatedCity.getCountryCode());
            city.setDistrict(updatedCity.getDistrict());
        }
        return cityRepository.save(city);
    }

    @DeleteMapping("/city/deleteCity/{id}")
    public String deleteCity(@PathVariable Integer id) {
        Optional<CityDTO> checkCity = cityRepository.findById(id);
        if(checkCity.isPresent()) {
            cityRepository.delete(checkCity.get());
            return "Deleted: " + checkCity.get();
        } else {
            return "City not found";
        }
    }

    @GetMapping("/city/perCountry")
    public Integer countCitiesPerCountry(@RequestParam (name = "code", required = true ) String code) {
        Optional<CountryDTO> country= countryRepository.findById(code);
        Integer counter = 0;
        if(country.isPresent()) {
            List<CityDTO> targetCities = cityRepository.findByCountryCode(country.get());

            for (CityDTO city : targetCities) {
                counter++;
            }
        }

        return counter;
    }
}
