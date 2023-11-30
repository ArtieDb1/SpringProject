package com.sparta.springtasticsix.springproject.controllers;


import com.sparta.springtasticsix.springproject.model.entities.CityDTO;
import com.sparta.springtasticsix.springproject.model.entities.CountryDTO;
import com.sparta.springtasticsix.springproject.model.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CityController {

    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
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

}
